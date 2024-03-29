package tipitapi.drawmytodayimprovement.table.auth.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.Auth;
import tipitapi.drawmytodayimprovement.table.auth.entity.AuthEntity;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.EntityManager;

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
