package tipitapi.drawmytodayimprovement.usecase;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.User;
import tipitapi.drawmytodayimprovement.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.exception.UserNotFoundException;
import tipitapi.drawmytodayimprovement.service.GoogleOAuthService;
import tipitapi.drawmytodayimprovement.service.RegisterUserService;
import tipitapi.drawmytodayimprovement.service.ValidateUserService;
import tipitapi.drawmytodayimprovement.service.dto.GoogleAccessToken;
import tipitapi.drawmytodayimprovement.service.dto.GoogleUserProfile;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

@Component
@RequiredArgsConstructor
public class AuthUseCase {

	private final GoogleOAuthService googleOAuthService;
	private final ValidateUserService validateUserService;
	private final RegisterUserService registerUserService;

	public JwtTokenInfo googleLogin(String authCode) {
		GoogleAccessToken accessToken = googleOAuthService.getAccessToken(authCode);
		GoogleUserProfile userProfile = googleOAuthService.getUserProfile(accessToken);

		User user = null;
		try {
			user = validateUserService.validateByEmailAndSocialCode(
				userProfile.email(), SocialCode.GOOGLE);
		} catch (UserNotFoundException e) {
			user = registerUserService.registerUser(
				userProfile.email(), SocialCode.GOOGLE, accessToken.refreshToken());
		}

		return JwtTokenInfo.of(user.getUserId(), user.getUserRole());
	}
}
