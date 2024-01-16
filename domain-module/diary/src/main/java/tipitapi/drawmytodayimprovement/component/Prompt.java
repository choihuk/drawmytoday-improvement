package tipitapi.drawmytodayimprovement.component;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Prompt {

	private Long promptId;
	private LocalDateTime createdAt;
	private Long diaryId;
	private String promptText;
	private boolean isSuccess;

	@Builder
	private Prompt(Long promptId, LocalDateTime createdAt, Long diaryId, String promptText, boolean isSuccess) {
		this.promptId = promptId;
		this.createdAt = createdAt;
		this.diaryId = diaryId;
		this.promptText = promptText;
		this.isSuccess = isSuccess;
	}

	public static Prompt createSuccessPrompt(Long diaryId, String prompt) {
		return Prompt.builder()
			.diaryId(diaryId)
			.promptText(prompt)
			.isSuccess(true)
			.build();
	}

	public static Prompt createFailedPrompt(String prompt) {
		return Prompt.builder()
			.promptText(prompt)
			.isSuccess(false)
			.build();
	}
}
