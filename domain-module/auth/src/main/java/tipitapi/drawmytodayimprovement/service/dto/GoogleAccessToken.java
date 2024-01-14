package tipitapi.drawmytodayimprovement.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * google access token response { access_token: "Sample access token", expires_in: 3599,
 * refresh_token: "Sample refresh token", scope: "https://www.googleapis.com/auth/indexing",
 * token_type: "Bearer" }
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleAccessToken {

    private String accessToken;
    private int expiresIn;
    private String refreshToken;
    private String tokenType;

}
