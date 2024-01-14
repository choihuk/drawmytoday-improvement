package tipitapi.drawmytodayimprovement.domain.auth.response;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTokenResponse {

    @NotBlank
    private final String accessToken;

    @NotBlank
    private final String refreshToken;

    public static JwtTokenResponse of(String accessToken, String refreshToken) {
        return new JwtTokenResponse(accessToken, refreshToken);
    }
}
