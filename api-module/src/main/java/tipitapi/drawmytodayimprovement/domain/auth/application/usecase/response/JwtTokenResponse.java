package tipitapi.drawmytodayimprovement.domain.auth.application.usecase.response;

import javax.validation.constraints.NotBlank;

public record JwtTokenResponse(@NotBlank String accessToken, @NotBlank String refreshToken) {

    public static JwtTokenResponse of(String accessToken, String refreshToken) {
        return new JwtTokenResponse(accessToken, refreshToken);
    }
}
