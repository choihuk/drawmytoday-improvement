package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.exception.DiaryDateAlreadyExistsException;
import tipitapi.drawmytodayimprovement.exception.DiaryNotFoundException;
import tipitapi.drawmytodayimprovement.exception.NotOwnerOfDiaryException;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiaryValidator {

    private final UserValidator userValidator;
    private final EmotionValidator emotionValidator;
    private final DiaryRepository diaryRepository;

    public Diary isDiaryOwner(Long diaryId, Long userId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(DiaryNotFoundException::new);
        if (!Objects.equals(diary.getUserId(), userId)) {
            throw new NotOwnerOfDiaryException();
        }
        return diary;
    }

    public void isDiaryNotExist(Long userId, LocalDate diaryDate) {
        if (diaryRepository.existByUserIdAndDiaryDate(userId, diaryDate)) {
            throw new DiaryDateAlreadyExistsException();
        }
    }

    public Emotion validateBeforeCreate(Long userId, Long emotionId, LocalDate diaryDate) {
        userValidator.validateByUserId(userId);
        Emotion emotion = emotionValidator.validateByEmotionId(emotionId);
        this.isDiaryNotExist(userId, diaryDate);
        return emotion;
    }
}
