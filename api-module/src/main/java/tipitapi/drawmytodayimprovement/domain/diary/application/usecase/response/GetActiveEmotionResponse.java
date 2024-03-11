package tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import tipitapi.drawmytodayimprovement.domain.Emotion;

@Builder
@Schema(description = "감정 목록 조회 Response. 활성화되어 일기 생성시 옵션으로 제공할 감정 목록")
public record GetActiveEmotionResponse(
        @Schema(description = "감정 ID", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "감정 이름", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(description = "감정 색깔 HEX", requiredMode = Schema.RequiredMode.REQUIRED)
        String color,
        @Schema(description = "감정 색깔 프롬프트값", requiredMode = Schema.RequiredMode.REQUIRED)
        String colorPrompt
) {
    public static GetActiveEmotionResponse from(Emotion emotion) {
        return GetActiveEmotionResponse.builder()
                .id(emotion.getEmotionId())
                .name(emotion.getName())
                .color(emotion.getColor())
                .colorPrompt(emotion.getColorPrompt())
                .build();
    }
}
