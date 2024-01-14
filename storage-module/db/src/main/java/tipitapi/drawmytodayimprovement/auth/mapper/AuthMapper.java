package tipitapi.drawmytodayimprovement.auth.mapper;

import org.springframework.stereotype.Component;

import tipitapi.drawmytodayimprovement.auth.entity.AuthEntity;
import tipitapi.drawmytodayimprovement.domain.Auth;

@Component
public class AuthMapper {

	public Auth mapToAuth(AuthEntity authEntity) {
		return Auth.builder()
			.authId(authEntity.getId())
			.userId(authEntity.getUserEntity().getId())
			.refreshToken(authEntity.getRefreshToken())
			.build();
	}
}
