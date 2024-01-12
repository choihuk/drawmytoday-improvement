package tipitapi.drawmytodayimprovement.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Emotion {

	private Long emotionId;
	private LocalDateTime createdAt;
	private String name;
	private String color;
	private boolean isActive;
	private String emotionPrompt;
	private String colorPrompt;
}
