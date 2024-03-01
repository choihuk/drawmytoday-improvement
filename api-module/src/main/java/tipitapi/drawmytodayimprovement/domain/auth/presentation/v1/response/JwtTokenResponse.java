package tipitapi.drawmytodayimprovement.domain.auth.presentation.v1.response;

import javax.validation.constraints.NotBlank;

public record JwtTokenResponse(@NotBlank String accessToken, @NotBlank String refreshToken) {

    public static JwtTokenResponse of(String accessToken, String refreshToken) {
        return new JwtTokenResponse(accessToken, refreshToken);
    }
}
