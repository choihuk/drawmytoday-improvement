package tipitapi.drawmytodayimprovement.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.DiaryUseCase;
import tipitapi.drawmytodayimprovement.component.AllDiaryElement;
import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.component.Prompt;
import tipitapi.drawmytodayimprovement.event.AfterCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.BeforeCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorService;

@Component
@RequiredArgsConstructor
class DiaryUseCaseImpl implements DiaryUseCase {

	private final ApplicationEventPublisher applicationEventPublisher;

	private final ValidateDiaryService validateDiaryService;
	private final ValidateUserService validateUserService;
	private final ValidateEmotionService validateEmotionService;
	private final PromptTextService promptTextService;
	private final ImageGeneratorService karloService;
	private final PromptService promptService;
	private final SaveDiaryService saveDiaryService;
	private final UserService userService;
	private final ImageService imageService;

	@Override
	@Transactional(readOnly = true)
	public AllDiaryElement getDiary(Long userId, Long diaryId) {
		validateUserService.validateByUserId(userId);
		Diary diary = validateDiaryService.validateDiaryById(diaryId, userId);
		Emotion emotion = validateEmotionService.validateByDiaryId(diaryId);
		Prompt prompt = promptService.get(diaryId).orElse(Prompt.createEmptyPrompt());
		List<Image> images = imageService.getLatestSortedImages(diaryId);
		return AllDiaryElement.of(diary, emotion, prompt, images);
	}

	@Override
	public Long createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement, LocalTime userTime) {
		validateUserService.validateByUserId(userId);
		Emotion emotion = validateEmotionService.validateById(emotionId);
		validateDiaryService.validateDiaryNotExist(userId, diaryElement.diaryDate());
		// validate available ticket left
		applicationEventPublisher.publishEvent(BeforeCreateDiaryEvent.of(userId));

		String promptText = promptTextService.createPromptText(emotion, diaryElement.keyword());
		List<byte[]> generatedImages = null;
		try {
			generatedImages = karloService.generateImage(promptText);
		} catch (ImageGeneratorException e) {
			promptService.createFailedPrompt(promptText);
			throw e;
		}

		Long diaryId = saveDiaryService.saveAll(userId, emotionId, diaryElement,
			userTime, promptText, generatedImages);

		// use ticket
		applicationEventPublisher.publishEvent(AfterCreateDiaryEvent.of(userId));
		return diaryId;
	}
}
