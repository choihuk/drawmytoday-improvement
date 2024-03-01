package tipitapi.drawmytodayimprovement.domain.auth.application.usecase;

import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

public interface AuthUseCase {
    JwtTokenInfo googleLogin(String authCode);
}
