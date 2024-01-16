package tipitapi.drawmytodayimprovement.domain.diary.request;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;

public record CreateDiaryRequest(@NotNull Long emotionId,
								 String keyword,
								 @Size(max = 6010) String notes,
								 @NotNull
								 // @ValidDiaryDate
								 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
								 @JsonSerialize(using = LocalDateSerializer.class)
								 @JsonDeserialize(using = LocalDateDeserializer.class)
								 LocalDate diaryDate,
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
