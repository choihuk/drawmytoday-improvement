package tipitapi.drawmytodayimprovement.domain.ticket.presentation.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;

@SecurityRequirement(name = "Bearer Authentication")
public interface AdRewardApi {


    @Operation(summary = "광고 기록 생성", description = "사용자가 광고를 시청한 후에 광고 기록을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "성공적으로 광고 기록을 등록함"),
    })
    @PostMapping
    ResponseEntity<Void> createAdReward(
            @AuthUser JwtTokenInfo tokenInfo
    );
}
