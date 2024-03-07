package tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import tipitapi.drawmytodayimprovement.domain.Diary;

import java.time.LocalDateTime;

@Schema(description = "마지막 일기 생성 시각 조회 Response")
public record GetLastCreationResponse(
        @Schema(description = "마지막 일기 생성 날짜", nullable = true, requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime lastCreation
) {

    public static GetLastCreationResponse of(Diary diary) {
        return new GetLastCreationResponse(diary.getCreatedAt());
    }

    public static GetLastCreationResponse ofEmpty() {
        return new GetLastCreationResponse(null);
    }
}
