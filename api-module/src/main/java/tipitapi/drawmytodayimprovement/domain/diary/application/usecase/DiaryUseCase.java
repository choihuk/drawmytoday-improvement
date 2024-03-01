package tipitapi.drawmytodayimprovement.domain.diary.application.usecase;

import tipitapi.drawmytodayimprovement.domain.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetDiaryExistByDateResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetMonthlyDiaryResponse;

import java.time.LocalDate;
import java.util.List;

public interface DiaryUseCase {
    GetDiaryResponse getDiary(Long userId, Long diaryId);

    CreateDiaryResponse createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement);

    List<GetMonthlyDiaryResponse> getMonthlyDiaries(Long userId, int year, int month);

    void updateDiaryNotes(Long userId, Long diaryId, String notes);

    GetDiaryExistByDateResponse getDiaryExistByDate(Long userId, LocalDate diaryDate);
}
