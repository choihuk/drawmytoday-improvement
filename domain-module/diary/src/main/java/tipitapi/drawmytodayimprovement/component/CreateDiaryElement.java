package tipitapi.drawmytodayimprovement.component;

import java.time.LocalDate;

public record CreateDiaryElement(String keyword,
								 String notes,
								 LocalDate diaryDate) {

}
