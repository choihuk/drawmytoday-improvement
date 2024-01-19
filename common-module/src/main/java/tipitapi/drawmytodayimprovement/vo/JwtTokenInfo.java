package tipitapi.drawmytodayimprovement.vo;

import java.util.Objects;

import tipitapi.drawmytodayimprovement.enumeration.UserRole;

public record JwtTokenInfo(Long userId, UserRole userRole) {

	public JwtTokenInfo {
		Objects.requireNonNull(userId);
		Objects.requireNonNull(userRole);
	}

	public static JwtTokenInfo of(Long userId, UserRole userRole) {
		return new JwtTokenInfo(userId, userRole);
	}
}
