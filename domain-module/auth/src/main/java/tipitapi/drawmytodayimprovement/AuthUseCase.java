package tipitapi.drawmytodayimprovement;

import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

public interface AuthUseCase {
	JwtTokenInfo googleLogin(String authCode);
}
