package tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "일기 생성 가능 여부 Response")
public record GetDiaryLimitResponse(
        @Schema(description = "일기 생성 가능 여부", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean available,
        @Schema(description = "마지막 일기 작성 시간", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime lastDiaryCreatedAt,
        @Schema(description = "유효 티켓 생성일자", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime ticketCreatedAt
) {
    public static GetDiaryLimitResponse of(boolean available, LocalDateTime lastDiaryDate,
                                           LocalDateTime ticketCreatedAt) {
        return new GetDiaryLimitResponse(available, lastDiaryDate, ticketCreatedAt);
    }
}
