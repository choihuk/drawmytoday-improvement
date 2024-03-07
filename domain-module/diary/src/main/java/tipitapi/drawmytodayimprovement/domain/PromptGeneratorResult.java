package tipitapi.drawmytodayimprovement.domain;

import lombok.Builder;

public class PromptGeneratorResult {


    private PromptGeneratorType promptGeneratorType;
    private String promptGeneratorContent;

    @Builder
    private PromptGeneratorResult(PromptGeneratorType promptGeneratorType,
                                  String promptGeneratorContent) {
        this.promptGeneratorType = promptGeneratorType;
        this.promptGeneratorContent = promptGeneratorContent;
    }

    public static PromptGeneratorResult createGpt3Result(String gptResult) {
        return new PromptGeneratorResult(PromptGeneratorType.GPT3, gptResult);
    }

    public static PromptGeneratorResult createNoUse() {
        return new PromptGeneratorResult(PromptGeneratorType.NONE, "");
    }

    public PromptGeneratorType getPromptGeneratorType() {
        return promptGeneratorType;
    }

    public String getPromptGeneratorContent() {
        return promptGeneratorContent;
    }
}
