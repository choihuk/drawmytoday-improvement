package tipitapi.drawmytodayimprovement.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * google access token response { access_token: "Sample access token", expires_in: 3599,
 * refresh_token: "Sample refresh token", scope: "https://www.googleapis.com/auth/indexing",
 * token_type: "Bearer" }
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record GoogleAccessToken(String accessToken, int expiresIn, String refreshToken, String tokenType) {

}
