package tipitapi.drawmytodayimprovement.domain.diary.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import tipitapi.drawmytodayimprovement.domain.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetDiaryExistByDateResponse;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetDiaryLimitResponse;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetLastCreationResponse;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetMonthlyDiaryResponse;
import tipitapi.drawmytodayimprovement.dto.MonthlyDiary;
import tipitapi.drawmytodayimprovement.event.AfterCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.AfterRegenerateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.BeforeCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.BeforeRegenerateDiaryEvent;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorService;
import tipitapi.drawmytodayimprovement.service.DiaryCreator;
import tipitapi.drawmytodayimprovement.service.DiaryService;
import tipitapi.drawmytodayimprovement.service.DiaryValidator;
import tipitapi.drawmytodayimprovement.service.EmotionValidator;
import tipitapi.drawmytodayimprovement.service.ImageService;
import tipitapi.drawmytodayimprovement.service.PromptService;
import tipitapi.drawmytodayimprovement.service.PromptTextCreator;
import tipitapi.drawmytodayimprovement.service.TicketService;
import tipitapi.drawmytodayimprovement.service.UserValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class DiaryUseCaseImpl implements DiaryUseCase {

	private final ApplicationEventPublisher applicationEventPublisher;
	private final TransactionTemplate readTransactionTemplate;
	private final TransactionTemplate writeTransactionTemplate;

	private final DiaryValidator diaryValidator;
	private final UserValidator userValidator;
	private final EmotionValidator emotionValidator;
	private final PromptTextCreator promptTextCreator;
	private final DiaryCreator diaryCreator;
	private final ImageGeneratorService karloService;
	private final PromptService promptService;
	private final DiaryService diaryService;
	private final ImageService imageService;

	// 의존하면 안 되는 클래스(리팩토링 필요)
	private final TicketService ticketService;

	@Override
	@Transactional(readOnly = true)
	public GetDiaryResponse getDiary(Long userId, Long diaryId) {
		userValidator.validateByUserId(userId);
		Diary diary = diaryValidator.isDiaryOwner(diaryId, userId);
		Emotion emotion = emotionValidator.validateByDiaryId(diaryId);
		List<Image> images = imageService.getLatestSortedImages(diaryId);
		Image selectedImage = imageService.getSelectedImage(images);
		Prompt prompt = promptService.getOrElseEmptyPrompt(selectedImage.getImageId());
		return GetDiaryResponse.of(diary, emotion, prompt, images, selectedImage);
	}

	@Override
	public CreateDiaryResponse createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement) {
		Emotion emotion = readTransactionTemplate.execute(status -> {
			try {
				Emotion validatedEmotion = diaryValidator.validateBeforeCreateDiary(userId, emotionId,
						diaryElement.diaryDate());
				// validate is available ticket left
				applicationEventPublisher.publishEvent(BeforeCreateDiaryEvent.of(userId));
				return validatedEmotion;
			} catch (Exception e) {
				status.setRollbackOnly();
				throw e;
			}
		});

		Prompt prompt = promptTextCreator.createPromptUsingGpt(emotion, diaryElement.diaryNote());
		List<byte[]> generatedImages = generateImages(prompt);

		Long diaryId = writeTransactionTemplate.execute(status -> {
			try {
				Long diaryId1 = diaryCreator.saveAfterCreateDiary(userId, emotionId, diaryElement,
						prompt, generatedImages);

				// use ticket
				applicationEventPublisher.publishEvent(AfterCreateDiaryEvent.of(userId));
				return diaryId1;
			} catch (Exception e) {
				status.setRollbackOnly();
				throw e;
			}
		});
		return CreateDiaryResponse.of(diaryId);
	}

	@Override
	public void regenerateDiaryImage(Long userId, Long diaryId, String diaryNote) {
		Emotion emotion = readTransactionTemplate.execute(status -> {
			try {
				Emotion validatedEmotion = diaryValidator.validateBeforeRegenerateDiary(userId, diaryId);

				// validate is available ticket left
				applicationEventPublisher.publishEvent(BeforeRegenerateDiaryEvent.of(userId));
				return validatedEmotion;
			} catch (Exception e) {
				status.setRollbackOnly();
				throw e;
			}
		});

		Prompt prompt = promptTextCreator.createPromptUsingGpt(emotion, diaryNote);
		List<byte[]> generatedImages = generateImages(prompt);

		writeTransactionTemplate.executeWithoutResult(status -> {
			try {
				diaryCreator.saveAfterRegenerateDiary(diaryId, prompt, generatedImages);

				// use ticket
				applicationEventPublisher.publishEvent(AfterRegenerateDiaryEvent.of(userId));
			} catch (Exception e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}

	private List<byte[]> generateImages(Prompt prompt) {
		List<byte[]> generatedImages;
		try {
			generatedImages = karloService.generateImage(prompt.getPromptText());
		} catch (ImageGeneratorException ige) {
			writeTransactionTemplate.executeWithoutResult(status -> {
				try {
					promptService.save(prompt);
				} catch (Exception e) {
					status.setRollbackOnly();
					ige.addSuppressed(e);
					throw ige;
				}
			});
			throw ige;
		}
		prompt.imageGeneratorSuccess();
		return generatedImages;
	}

	@Override
	@Transactional
	public List<GetMonthlyDiaryResponse> getMonthlyDiaries(Long userId, int year, int month) {
		userValidator.validateByUserId(userId);
		List<MonthlyDiary> monthlyDiaries = diaryService.getMonthlyDiaries(userId, year, month);
		return imageService.fillImages(monthlyDiaries).stream()
				.map(GetMonthlyDiaryResponse::of)
				.toList();
	}

	@Override
	@Transactional
	public void updateDiaryNotes(Long userId, Long diaryId, String notes) {
		userValidator.validateByUserId(userId);
		Diary diary = diaryValidator.isDiaryOwner(diaryId, userId);
		diary.updateNotes(notes);
		diaryCreator.save(diary);
	}

	@Override
	@Transactional
	public void deleteDiary(Long userId, Long diaryId) {
		userValidator.validateByUserId(userId);
		Diary diary = diaryValidator.isDiaryOwner(diaryId, userId);
		diaryService.deleteDiary(diary);
	}

	@Override
	@Transactional(readOnly = true)
	public GetDiaryExistByDateResponse getDiaryExistByDate(Long userId, LocalDate diaryDate) {
		userValidator.validateByUserId(userId);
		return diaryService.getDiaryExistsByDiaryDate(userId, diaryDate)
				.map(GetDiaryExistByDateResponse::ofExist)
				.orElseGet(GetDiaryExistByDateResponse::ofNotExist);
	}

	@Override
	@Transactional(readOnly = true)
	public GetLastCreationResponse getLastCreation(Long userId) {
		userValidator.validateByUserId(userId);
		return diaryService.getLastCreation(userId)
				.map(GetLastCreationResponse::of)
				.orElseGet(GetLastCreationResponse::ofEmpty);
	}

	@Override
	@Transactional(readOnly = true)
	public GetDiaryLimitResponse getDrawLimit(Long userId) {
		User user = userValidator.validateByUserId(userId);
		Optional<Ticket> ticket = ticketService.findAvailableTicket(userId);

		boolean available = ticket.isPresent();
		LocalDateTime lastDiaryDate = user.getLastDiaryDate();
		LocalDateTime ticketCreatedAt = ticket
				.map(Ticket::getCreatedAt)
				.orElse(null);
		return GetDiaryLimitResponse.of(available, lastDiaryDate, ticketCreatedAt);
	}
}
