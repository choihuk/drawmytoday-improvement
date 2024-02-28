package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
    private final UserValidator userValidator;
    private final RegisterUserService registerUserService;

    @Override
    public JwtTokenInfo googleLogin(String authCode) {
        GoogleAccessToken accessToken = googleOAuthService.getAccessToken(authCode);
        GoogleUserProfile userProfile = googleOAuthService.getUserProfile(accessToken.accessToken());

        User user = null;
        try {
            user = userValidator.validateByEmailAndSocialCode(
                    userProfile.email(), SocialCode.GOOGLE);
        } catch (UserNotFoundException e) {
            user = registerUserService.registerUser(
                    userProfile.email(), SocialCode.GOOGLE, accessToken.refreshToken());
        }

        return JwtTokenInfo.of(user.getUserId(), user.getUserRole());
    }
}
