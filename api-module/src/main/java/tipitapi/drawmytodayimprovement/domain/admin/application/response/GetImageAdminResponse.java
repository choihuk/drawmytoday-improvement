package tipitapi.drawmytodayimprovement.domain.admin.application.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import tipitapi.drawmytodayimprovement.dto.ImageForMonitoring;

import java.time.LocalDateTime;

public record GetImageAdminResponse(
        @Schema(description = "일기 ID", requiredMode = Schema.RequiredMode.REQUIRED)
        Long id,
        @Schema(description = "일기 프롬프트", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String prompt,
        @Schema(description = "일기 작성 시간", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime createdAt,
        @Schema(description = "이미지 생성 시간", requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime imageCreatedAt,
        @Schema(description = "일기 이미지 URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String imageURL,
        @Schema(description = "평가 점수 (1~5 사이의 숫자)")
        String review,
        @Schema(description = "해당 이미지가 테스트 전용 이미지인지 여부", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean isTest
) {
    public static GetImageAdminResponse from(ImageForMonitoring imageForMonitoring) {
        return new GetImageAdminResponse(
                imageForMonitoring.diaryId(),
                imageForMonitoring.prompt(),
                imageForMonitoring.createdAt(),
                imageForMonitoring.imageCreatedAt(),
                imageForMonitoring.imageUrl(),
                imageForMonitoring.review(),
                imageForMonitoring.isTest()
        );
    }

}
