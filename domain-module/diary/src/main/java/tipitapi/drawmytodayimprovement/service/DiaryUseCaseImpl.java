package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import tipitapi.drawmytodayimprovement.DiaryUseCase;
import tipitapi.drawmytodayimprovement.component.*;
import tipitapi.drawmytodayimprovement.event.AfterCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.BeforeCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorService;

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
    private final PromptTextService promptTextService;
    private final ImageGeneratorService karloService;
    private final PromptService promptService;
    private final DiaryCreator diaryCreator;
    //    private final DiaryService diaryService;
    private final MonthlyDiaryService montyDiaryService;
    private final ImageService imageService;

    @Override
    @Transactional(readOnly = true)
    public AllDiaryElement getDiary(Long userId, Long diaryId) {
        userValidator.validateByUserId(userId);
        Diary diary = diaryValidator.isDiaryOwner(diaryId, userId);
        Emotion emotion = emotionValidator.validateByDiaryId(diaryId);
        Prompt prompt = promptService.getOrElseEmptyPrompt(diaryId);
        List<Image> images = imageService.getLatestSortedImages(diaryId);
        return AllDiaryElement.of(diary, emotion, prompt, images);
    }

    @Override
    public Long createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement) {
        Emotion emotion = readTransactionTemplate.execute(status -> {
            try {
                Emotion validatedEmotion = diaryValidator.validateBeforeCreate(userId, emotionId,
                        diaryElement.diaryDate());

                // validate is available ticket left
                applicationEventPublisher.publishEvent(BeforeCreateDiaryEvent.of(userId));
                return validatedEmotion;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
        });

        String promptText = promptTextService.createPromptText(emotion, diaryElement.keyword());
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
                Long diaryId1 = diaryCreator.saveAfterCreate(userId, emotionId, diaryElement,
                        promptText, generatedImages);

                // use ticket
                applicationEventPublisher.publishEvent(AfterCreateDiaryEvent.of(userId));
                return diaryId1;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
        });
        return diaryId;
    }

    @Override
    @Transactional
    public List<MonthlyDiary> getMonthlyDiaries(Long userId, int year, int month) {
        userValidator.validateByUserId(userId);
        List<MonthlyDiary> monthlyDiaries = montyDiaryService.getMonthlyDiaries(userId, year, month);
        return imageService.fillImages(monthlyDiaries);
    }

    @Override
    @Transactional
    public void updateDiaryNotes(Long userId, Long diaryId, String notes) {
        userValidator.validateByUserId(userId);
        Diary diary = diaryValidator.isDiaryOwner(diaryId, userId);
        diary.updateNotes(notes);
        diaryCreator.save(diary);
    }
}
