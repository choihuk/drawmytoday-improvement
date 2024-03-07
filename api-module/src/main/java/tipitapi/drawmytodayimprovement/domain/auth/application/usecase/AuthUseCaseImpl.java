package tipitapi.drawmytodayimprovement.domain.auth.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.domain.SocialCode;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.exception.UserNotFoundException;
import tipitapi.drawmytodayimprovement.oauth.GoogleOAuthService;
import tipitapi.drawmytodayimprovement.oauth.google.GoogleAccessToken;
import tipitapi.drawmytodayimprovement.oauth.google.GoogleUserProfile;
import tipitapi.drawmytodayimprovement.service.RegisterUserService;
import tipitapi.drawmytodayimprovement.service.UserValidator;

@Component
@RequiredArgsConstructor
class AuthUseCaseImpl implements AuthUseCase {

    private final GoogleOAuthService googleOAuthService;
    private final UserValidator userValidator;
    private final RegisterUserService registerUserService;
    private final TransactionTemplate readTransactionTemplate;
    private final TransactionTemplate writeTransactionTemplate;

    @Override
    public JwtTokenInfo googleLogin(String authCode) {
        GoogleAccessToken accessToken = googleOAuthService.getAccessToken(authCode);
        GoogleUserProfile userProfile = googleOAuthService.getUserProfile(accessToken.accessToken());

        User user = null;
        try {
            user = readTransactionTemplate.execute(status ->
                    userValidator.validateByEmailAndSocialCode(userProfile.email(), SocialCode.GOOGLE)
            );
        } catch (UserNotFoundException e) {
            user = writeTransactionTemplate.execute(status ->
                    registerUserService.registerUser(
                            userProfile.email(), SocialCode.GOOGLE, accessToken.refreshToken())
            );
        }

        return JwtTokenInfo.of(user.getUserId(), user.getUserRole());
    }
}
