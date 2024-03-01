package tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "일기 생성 Response")
public record CreateDiaryResponse(@Schema(description = "일기 ID", requiredMode = Schema.RequiredMode.REQUIRED)
                                  Long id) {

    public static CreateDiaryResponse of(Long id) {
        return new CreateDiaryResponse(Objects.requireNonNull(id));
    }
}
