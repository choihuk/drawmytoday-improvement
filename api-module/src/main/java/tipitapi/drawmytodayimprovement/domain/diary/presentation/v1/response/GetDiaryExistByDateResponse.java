package tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;
import tipitapi.drawmytodayimprovement.domain.Diary;

@Schema(description = "특정 날짜 일기 존재 여부 조회 Response")
public record GetDiaryExistByDateResponse(
        @Schema(description = "일기 존재 여부", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean exist,
        @Schema(description = "일기 아이디", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Long diaryId) {

    public static GetDiaryExistByDateResponse ofExist(Diary diary) {
        return new GetDiaryExistByDateResponse(true, diary.getDiaryId());
    }

    public static GetDiaryExistByDateResponse ofNotExist() {
        return new GetDiaryExistByDateResponse(false, null);
    }
}
