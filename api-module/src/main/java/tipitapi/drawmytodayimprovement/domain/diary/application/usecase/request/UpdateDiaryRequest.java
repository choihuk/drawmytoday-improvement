package tipitapi.drawmytodayimprovement.domain.diary.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;

@Schema(description = "일기 수정 Request")
public record UpdateDiaryRequest(@Schema(description = "일기 내용. null로 요청할 경우, 일기의 내용을 null로 변경한다.", nullable = true)
                                 @Size(max = 6010)
                                 String notes) {

}
