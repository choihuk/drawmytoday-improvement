package tipitapi.drawmytodayimprovement.domain.diary.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import tipitapi.drawmytodayimprovement.common.validator.ValidDiaryDate;
import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;

public record CreateDiaryRequest(@Schema(description = "감정 ID")
								 @NotNull
								 Long emotionId,
								 @Schema(description = "일기 키워드", nullable = true)
								 String keyword,
								 @Schema(description = "일기 내용", nullable = true)
								 @Size(max = 6010)
								 String notes,
								 @Schema(description = "일기 날짜")
								 @NotNull
								 @ValidDiaryDate
								 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
								 @JsonDeserialize(using = LocalDateDeserializer.class)
								 LocalDate diaryDate,
								 @Schema(description = "현재 유저 시간", nullable = true, example = "12:00:00")
								 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
								 @JsonDeserialize(using = LocalTimeDeserializer.class)
								 LocalTime userTime) {

	public CreateDiaryElement toCreateDiaryElement() {
		return new CreateDiaryElement(keyword, notes, diaryDate);
	}

	public LocalTime userTime() {
		if (userTime == null) {
			return LocalTime.now();
		}
		return userTime;
	}
}