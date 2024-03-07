package tipitapi.drawmytodayimprovement.domain.diary.application.usecase;

import tipitapi.drawmytodayimprovement.domain.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.*;

import java.time.LocalDate;
import java.util.List;

public interface DiaryUseCase {
    GetDiaryResponse getDiary(Long userId, Long diaryId);

    CreateDiaryResponse createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement);

    List<GetMonthlyDiaryResponse> getMonthlyDiaries(Long userId, int year, int month);

    void updateDiaryNotes(Long userId, Long diaryId, String notes);

    void deleteDiary(Long userId, Long diaryId);

    GetDiaryExistByDateResponse getDiaryExistByDate(Long userId, LocalDate diaryDate);

    GetLastCreationResponse getLastCreation(Long userId);

    GetDiaryLimitResponse getDrawLimit(Long userId);

    void regenerateDiaryImage(Long userId, Long diaryId, String diaryNote);
}
