package tipitapi.drawmytodayimprovement.vo;

import tipitapi.drawmytodayimprovement.enumeration.UserRole;

public record JwtTokenInfo(Long userId, UserRole userRole) {

    public static JwtTokenInfo of(Long userId, UserRole userRole) {
        return new JwtTokenInfo(userId, userRole);
    }
}
