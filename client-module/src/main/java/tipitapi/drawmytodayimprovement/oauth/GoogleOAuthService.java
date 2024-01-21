package tipitapi.drawmytodayimprovement.oauth;

import tipitapi.drawmytodayimprovement.oauth.google.GoogleAccessToken;
import tipitapi.drawmytodayimprovement.oauth.google.GoogleUserProfile;

public interface GoogleOAuthService {
	GoogleAccessToken getAccessToken(String authCode);

	GoogleUserProfile getUserProfile(String accessToken);
}
