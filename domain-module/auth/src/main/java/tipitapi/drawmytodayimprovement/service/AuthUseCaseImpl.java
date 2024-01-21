package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.AuthUseCase;
import tipitapi.drawmytodayimprovement.component.User;
import tipitapi.drawmytodayimprovement.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.exception.UserNotFoundException;
import tipitapi.drawmytodayimprovement.oauth.GoogleOAuthService;
import tipitapi.drawmytodayimprovement.oauth.google.GoogleAccessToken;
import tipitapi.drawmytodayimprovement.oauth.google.GoogleUserProfile;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

@Component
@RequiredArgsConstructor
class AuthUseCaseImpl implements AuthUseCase {

	private final GoogleOAuthService googleOAuthService;
	private final ValidateUserService validateUserService;
	private final RegisterUserService registerUserService;

	@Override
	public JwtTokenInfo googleLogin(String authCode) {
		GoogleAccessToken accessToken = googleOAuthService.getAccessToken(authCode);
		GoogleUserProfile userProfile = googleOAuthService.getUserProfile(accessToken.accessToken());

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
