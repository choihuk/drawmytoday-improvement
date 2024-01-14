package tipitapi.drawmytodayimprovement.domain.vo;

import lombok.Getter;
import tipitapi.drawmytodayimprovement.domain.enumeration.UserRole;

@Getter
public class JwtTokenInfo {

    private final Long userId;
    private final UserRole userRole;

    private JwtTokenInfo(Long userId, UserRole userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }

    public static JwtTokenInfo of(Long userId, UserRole userRole) {
    	return new JwtTokenInfo(userId, userRole);
    }
}
