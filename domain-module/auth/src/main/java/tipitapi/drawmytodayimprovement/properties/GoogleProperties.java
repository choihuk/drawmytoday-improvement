package tipitapi.drawmytodayimprovement.properties;

import org.springframework.stereotype.Component;

import lombok.Getter;
import tipitapi.drawmytodayimprovement.domain.utils.SystemEnv;

@Getter
@Component
public class GoogleProperties {

	private final String clientId;
	private final String clientSecret;
	private final String tokenUrl;
	private final String userInfoUrl;
	private final String redirectUri;
	private final String deleteAccountUrl;

	public GoogleProperties() {
		this.clientId = SystemEnv.get("GOOGLE_CLIENT_ID");
		this.clientSecret = SystemEnv.get("GOOGLE_CLIENT_SECRET");
		this.tokenUrl = SystemEnv.get("GOOGLE_TOKEN_URL");
		this.userInfoUrl = SystemEnv.get("GOOGLE_USER_INFO_URL");
		this.redirectUri = SystemEnv.get("GOOGLE_REDIRECT_URI");
		this.deleteAccountUrl = SystemEnv.get("GOOGLE_DELETE_ACCOUNT_URL");
	}
}
