package tipitapi.drawmytodayimprovement.user.mapper;

import org.springframework.stereotype.Component;

import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

@Component
public class UserMapper {


	public User toDomain(UserEntity userEntity) {
		return User.builder()
				.userId(userEntity.getId())
				.createdAt(userEntity.getCreatedAt())
				.updatedAt(userEntity.getUpdatedAt())
				.email(userEntity.getEmail())
				.socialCode(userEntity.getSocialCode())
				.lastDiaryDate(userEntity.getLastDiaryDate())
				.userRole(userEntity.getUserRole())
				.deletedAt(userEntity.getDeletedAt())
				.build();
	}

	public UserEntity toEntity(User user) {
		return UserEntity.builder()
				.id(user.getUserId())
				.email(user.getEmail())
				.socialCode(user.getSocialCode())
				.lastDiaryDate(user.getLastDiaryDate())
				.userRole(user.getUserRole())
				.deletedAt(user.getDeletedAt())
				.build();
	}
}
