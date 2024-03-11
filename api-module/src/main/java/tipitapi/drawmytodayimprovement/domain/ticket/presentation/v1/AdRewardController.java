package tipitapi.drawmytodayimprovement.domain.ticket.presentation.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.domain.ticket.application.usecase.AdRewardUseCase;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdRewardController implements AdRewardApi {

    private final AdRewardUseCase adRewardUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> createAdReward(
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        adRewardUseCase.createAdReward(tokenInfo.userId());
        return ResponseEntity.noContent().build();
    }
}
