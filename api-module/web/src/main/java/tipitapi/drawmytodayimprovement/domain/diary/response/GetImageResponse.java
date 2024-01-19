package tipitapi.drawmytodayimprovement.domain.diary.response;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import tipitapi.drawmytodayimprovement.component.Image;

@Schema(description = "이미지 Response")
public record GetImageResponse(@Schema(description = "이미지 id", requiredMode = RequiredMode.REQUIRED)
							   Long id,
							   @Schema(description = "이미지 생성 시각", requiredMode = RequiredMode.REQUIRED)
							   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
							   @JsonSerialize(using = LocalDateTimeSerializer.class)
							   LocalDateTime createdAt,
							   @Schema(description = "대표 이미지 여부", requiredMode = RequiredMode.REQUIRED)
							   boolean selected,
							   @Schema(description = "이미지 URL", requiredMode = RequiredMode.REQUIRED)
							   String url) {

	public static GetImageResponse of(Image image) {
		Long imageId = Objects.requireNonNull(image.getImageId());
		LocalDateTime createdAt = Objects.requireNonNull(image.getCreatedAt());
		String imageUrl = Objects.requireNonNull(image.getImageUrl());
		return new GetImageResponse(imageId, createdAt, image.isSelected(), imageUrl);
	}
}
