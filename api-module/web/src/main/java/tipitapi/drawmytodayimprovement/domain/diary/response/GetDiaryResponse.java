package tipitapi.drawmytodayimprovement.domain.diary.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import tipitapi.drawmytodayimprovement.component.AllDiaryElement;
import tipitapi.drawmytodayimprovement.component.Diary;

@Schema(description = "일기 상세 Response")
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

	public static GetDiaryResponse of(AllDiaryElement element) {
		Diary diary = element.diary();

		Long diaryId = Objects.requireNonNull(diary.getDiaryId());
		String imageUrl = Objects.requireNonNull(element.selectedImage().getImageUrl());
		List<GetImageResponse> imageList = element.imageList()
			.stream()
			.map(GetImageResponse::of)
			.toList();
		LocalDateTime diaryDate = Objects.requireNonNull(diary.getDiaryDate());
		LocalDateTime createdAt = Objects.requireNonNull(diary.getCreatedAt());
		String emotionPrompt = Objects.requireNonNull(element.emotion().getEmotionPrompt());

		return new GetDiaryResponse(diaryId, imageUrl, imageList, diaryDate, createdAt, emotionPrompt,
			diary.getNotes(), element.prompt().getPromptText());
	}

}
