package tipitapi.drawmytodayimprovement;

import java.time.LocalTime;

import tipitapi.drawmytodayimprovement.component.AllDiaryElement;
import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;

public interface DiaryUseCase {
	AllDiaryElement getDiary(Long userId, Long diaryId);

	Long createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement, LocalTime userTime);
}
