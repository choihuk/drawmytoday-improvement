package tipitapi.drawmytodayimprovement.domain.vo;

import tipitapi.drawmytodayimprovement.domain.enumeration.UserRole;

public record JwtTokenInfo(Long userId, UserRole userRole) {

    public static JwtTokenInfo of(Long userId, UserRole userRole) {
        return new JwtTokenInfo(userId, userRole);
    }
}
