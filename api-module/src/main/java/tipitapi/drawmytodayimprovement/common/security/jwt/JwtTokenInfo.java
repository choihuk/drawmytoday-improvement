package tipitapi.drawmytodayimprovement.common.security.jwt;

import tipitapi.drawmytodayimprovement.domain.UserRole;

import java.util.Objects;

public record JwtTokenInfo(Long userId, UserRole userRole) {

    public JwtTokenInfo {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(userRole);
    }

    public static JwtTokenInfo of(Long userId, UserRole userRole) {
        return new JwtTokenInfo(userId, userRole);
    }
}
