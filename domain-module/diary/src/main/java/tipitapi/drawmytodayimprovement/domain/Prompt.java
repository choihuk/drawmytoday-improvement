package tipitapi.drawmytodayimprovement.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Prompt {

    private Long promptId;
    private LocalDateTime createdAt;
    private PromptGeneratorResult promptGeneratorResult;
    private String promptText;
    private boolean isSuccess;

    @Builder
    private Prompt(Long promptId, LocalDateTime createdAt, String promptText,
                   PromptGeneratorResult promptGeneratorResult, boolean isSuccess) {
        this.promptId = promptId;
        this.createdAt = createdAt;
        this.promptGeneratorResult = promptGeneratorResult;
        this.promptText = promptText;
        this.isSuccess = isSuccess;
    }

    public static Prompt createEmptyPrompt() {
        return Prompt.builder().build();
    }

    public static Prompt create(PromptGeneratorResult result, String promptText) {
        return Prompt.builder()
                .promptGeneratorResult(result)
                .promptText(promptText)
                .isSuccess(false)
                .build();
    }

    public void imageGeneratorSuccess() {
        this.isSuccess = true;
    }
}
