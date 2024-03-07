package tipitapi.drawmytodayimprovement.domain.auth.application.usecase;

import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;

public interface AuthUseCase {
    JwtTokenInfo googleLogin(String authCode);
}
