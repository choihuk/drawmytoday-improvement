package tipitapi.drawmytodayimprovement.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Diary {

	private Long diaryId;
	private LocalDateTime createdAt;
	private Emotion emotion;
	private Long userId;
	private LocalDateTime diaryDate;
	private String notes;
	private boolean isAi;
	private String title;
	private String weather;
	private LocalDateTime deletedAt;
	private boolean isTest;

}
