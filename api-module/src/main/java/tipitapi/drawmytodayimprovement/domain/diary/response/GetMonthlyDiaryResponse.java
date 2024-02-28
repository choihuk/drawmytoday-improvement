package tipitapi.drawmytodayimprovement.domain.diary.response;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import tipitapi.drawmytodayimprovement.component.MonthlyDiary;

@Schema(description = "월별 일기 목록 Response")
public record GetMonthlyDiaryResponse(@Schema(description = "일기 아이디", requiredMode = RequiredMode.REQUIRED)
									  Long id,
									  @Schema(description = "대표 이미지 URL", requiredMode = RequiredMode.REQUIRED)
									  String imageUrl,
									  @Schema(description = "일기 날짜", requiredMode = RequiredMode.REQUIRED)
									  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
									  @JsonSerialize(using = LocalDateTimeSerializer.class)
									  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
									  LocalDateTime date) {

	public GetMonthlyDiaryResponse {
		Objects.requireNonNull(id);
		Objects.requireNonNull(imageUrl);
		Objects.requireNonNull(date);
	}

	public static GetMonthlyDiaryResponse of(MonthlyDiary monthlyDiary) {
		return new GetMonthlyDiaryResponse(monthlyDiary.getDiaryId(), monthlyDiary.getImageUrl(),
			monthlyDiary.getDiaryDate());
	}
}
