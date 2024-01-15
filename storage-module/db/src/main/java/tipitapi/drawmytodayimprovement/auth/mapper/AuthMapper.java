package tipitapi.drawmytodayimprovement.auth.mapper;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.auth.entity.AuthEntity;
import tipitapi.drawmytodayimprovement.domain.Auth;
import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class AuthMapper {

	private final EntityManager entityManager;

	public Auth toDomain(AuthEntity authEntity) {
		return Auth.builder()
			.authId(authEntity.getId())
			.createdAt(authEntity.getCreatedAt())
			.userId(authEntity.getUserEntity().getId())
			.refreshToken(authEntity.getRefreshToken())
			.build();
	}

	public AuthEntity toEntity(Auth auth) {
		UserEntity userEntity = entityManager.getReference(UserEntity.class, auth.getUserId());
		return AuthEntity.builder()
			.id(auth.getAuthId())
			.userEntity(userEntity)
			.refreshToken(auth.getRefreshToken())
			.build();
	}
}
