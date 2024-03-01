package tipitapi.drawmytodayimprovement.domain.diary.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import tipitapi.drawmytodayimprovement.domain.*;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetDiaryExistByDateResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetMonthlyDiaryResponse;
import tipitapi.drawmytodayimprovement.event.AfterCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.BeforeCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorService;
import tipitapi.drawmytodayimprovement.service.*;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public GetDiaryResponse getDiary(Long userId, Long diaryId) {
        userValidator.validateByUserId(userId);
        Diary diary = diaryValidator.isDiaryOwner(diaryId, userId);
        Emotion emotion = emotionValidator.validateByDiaryId(diaryId);
        Prompt prompt = promptService.getOrElseEmptyPrompt(diaryId);
        List<Image> images = imageService.getLatestSortedImages(diaryId);
        return GetDiaryResponse.of(diary, emotion, prompt, images);
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

        String promptText = promptTextCreator.createPromptText(emotion, diaryElement.keyword());
        List<byte[]> generatedImages;
        try {
            generatedImages = karloService.generateImage(promptText);
        } catch (ImageGeneratorException ige) {
            writeTransactionTemplate.executeWithoutResult(status -> {
                try {
                    promptService.createFailedPrompt(promptText);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    ige.addSuppressed(e);
                    throw ige;
                }
            });
            throw ige;
        }

        Long diaryId = writeTransactionTemplate.execute(status -> {
            try {
                Long diaryId1 = diaryCreator.saveAfterCreateDiary(userId, emotionId, diaryElement,
                        promptText, generatedImages);

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
    @Transactional(readOnly = true)
    public GetDiaryExistByDateResponse getDiaryExistByDate(Long userId, LocalDate diaryDate) {
        userValidator.validateByUserId(userId);
        return diaryService.getDiaryExistsByDiaryDate(userId, diaryDate)
                .map(GetDiaryExistByDateResponse::ofExist)
                .orElseGet(GetDiaryExistByDateResponse::ofNotExist);
    }
}
