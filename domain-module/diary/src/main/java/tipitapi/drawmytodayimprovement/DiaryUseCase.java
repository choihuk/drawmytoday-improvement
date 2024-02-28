package tipitapi.drawmytodayimprovement;

import java.util.List;

import tipitapi.drawmytodayimprovement.component.AllDiaryElement;
import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.component.MonthlyDiary;

public interface DiaryUseCase {
	AllDiaryElement getDiary(Long userId, Long diaryId);

	Long createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement);

	List<MonthlyDiary> getMonthlyDiaries(Long userId, int year, int month);

	void updateDiaryNotes(Long userId, Long diaryId, String notes);
}
