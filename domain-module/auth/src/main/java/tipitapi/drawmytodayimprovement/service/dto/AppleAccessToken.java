package tipitapi.drawmytodayimprovement.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * apple access token response { "access_token": "adg61...67Or9", "token_type": "Bearer",
 * "expires_in": 3600, "refresh_token": "rca7...lABoQ", "id_token": "eyJra...96sZg" }
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record AppleAccessToken(String accessToken, int expiresIn, String refreshToken, String tokenType) {

}
