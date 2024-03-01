package tipitapi.drawmytodayimprovement.domain.auth.presentation.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.domain.auth.presentation.v1.response.JwtTokenResponse;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Auth", description = "인증 API")
public interface AuthApi {

    @Operation(summary = "구글 로그인", description = "프론트로부터 Authorization code를 받아 구글 로그인을 진행합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "구글 로그인 성공"),
            @ApiResponse(
                    responseCode = "400",
                    description = "C001 : 토큰 형식이 Bearer 형식이 아닙니다.",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(
                    responseCode = "404",
                    description = "S007 : Authorization header에 토큰이 비었습니다.",
                    content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(
                    responseCode = "500",
                    description = "O002 : 구글 OAuth 서버와의 통신에 실패했습니다.",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping(value = "/google/login")
    ResponseEntity<SuccessResponse<JwtTokenResponse>> googleLogin(HttpServletRequest request);
}
