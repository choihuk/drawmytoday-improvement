package tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AccessLevel;
import lombok.Builder;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.exception.ImageNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Schema(description = "일기 상세 Response")
@Builder(access = AccessLevel.PRIVATE)
public record GetDiaryResponse(@Schema(description = "일기 아이디", requiredMode = RequiredMode.REQUIRED)
                               Long id,
                               @Schema(description = "대표 이미지 URL", requiredMode = RequiredMode.REQUIRED)
                               String imageUrl,
                               @Schema(description = "대표 이미지를 포함한 이미지 URL 리스트",
                                       requiredMode = RequiredMode.NOT_REQUIRED)
                               List<GetImageResponse> imageList,
                               @Schema(description = "일기 날짜", requiredMode = RequiredMode.REQUIRED)
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
                               @JsonSerialize(using = LocalDateTimeSerializer.class)
                               LocalDateTime date,
                               @Schema(description = "일기 작성 시간", requiredMode = RequiredMode.REQUIRED)
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
                               @JsonSerialize(using = LocalDateTimeSerializer.class)
                               LocalDateTime createdAt,
                               @Schema(description = "감정", requiredMode = RequiredMode.REQUIRED)
                               String emotion,
                               @Schema(description = "일기 내용", requiredMode = RequiredMode.NOT_REQUIRED)
                               String notes,
                               @Schema(description = "프롬프트", requiredMode = RequiredMode.NOT_REQUIRED)
                               String prompt) {

    public GetDiaryResponse {
        Objects.requireNonNull(id);
        Objects.requireNonNull(imageUrl);
        Objects.requireNonNull(date);
        Objects.requireNonNull(createdAt);
        Objects.requireNonNull(emotion);
    }

    public static GetDiaryResponse of(Diary diary, Emotion emotion, Prompt prompt, List<Image> images) {
        String selectedImageUrl = images.stream()
                .filter(Image::isSelected)
                .findFirst()
                .orElseThrow(ImageNotFoundException::new)
                .getImageUrl();
        List<GetImageResponse> imageList = images.stream()
                .map(GetImageResponse::of)
                .toList();
        return GetDiaryResponse.builder()
                .id(diary.getDiaryId())
                .imageUrl(selectedImageUrl)
                .imageList(imageList)
                .date(diary.getDiaryDate())
                .createdAt(diary.getCreatedAt())
                .emotion(emotion.getEmotionPrompt())
                .notes(diary.getNotes())
                .prompt(prompt.getPromptText())
                .build();
    }
}
