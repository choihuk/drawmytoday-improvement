package tipitapi.drawmytodayimprovement.component;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateDiaryElement(String keyword,
								 String notes,
								 LocalDate diaryDate,
								 LocalTime userTime) {

	public LocalTime userTime() {
		if (userTime == null) {
			return LocalTime.now();
		}
		return userTime;
	}

}
