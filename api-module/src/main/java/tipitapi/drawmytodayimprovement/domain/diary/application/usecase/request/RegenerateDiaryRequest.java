package tipitapi.drawmytodayimprovement.domain.diary.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegenerateDiaryRequest(
        @Schema(description = "번역된 일기(만약 값이 없다면 이전에 생성한 프롬프트로 일기를 재생성하고 값이 있다면 "
                + "새로운 프롬프트를 만들어 일기를 재생성", requiredMode = Schema.RequiredMode.REQUIRED)
        String diary
) {
}
