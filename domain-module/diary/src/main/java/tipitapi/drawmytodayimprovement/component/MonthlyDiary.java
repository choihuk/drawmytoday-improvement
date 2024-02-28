package tipitapi.drawmytodayimprovement.component;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthlyDiary {

	private Long diaryId;
	private String imageUrl;
	private LocalDateTime diaryDate;

	public static MonthlyDiary of(Long diaryId, String imageUrl, LocalDateTime diaryDate) {
		return new MonthlyDiary(diaryId, imageUrl, diaryDate);
	}

	public void convertImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
