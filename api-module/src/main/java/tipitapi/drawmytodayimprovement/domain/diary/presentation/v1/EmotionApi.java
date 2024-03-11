package tipitapi.drawmytodayimprovement.domain.diary.presentation.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetActiveEmotionResponse;

import java.util.List;

public interface EmotionApi {

    @Operation(summary = "감정 목록 조회", description = "일기 생성시 노출할 감정의 목록을 반환한다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "감정 목록 정보"),
    })
    @GetMapping("/all")
    ResponseEntity<SuccessResponse<List<GetActiveEmotionResponse>>> getActiveEmotionResponses(
            @AuthUser JwtTokenInfo tokenInfo
    );
}
