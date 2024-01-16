package tipitapi.drawmytodayimprovement.adreward.mapper;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.adreward.entity.AdRewardEntity;
import tipitapi.drawmytodayimprovement.component.AdReward;
import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class AdRewardMapper {

	private final EntityManager entityManager;

	public AdReward toDomain(AdRewardEntity entity) {
		return AdReward.builder()
			.adRewardId(entity.getId())
			.createdAt(entity.getCreatedAt())
			.userId(entity.getUser().getId())
			.usedAt(entity.getUsedAt())
			.build();
	}

	public AdRewardEntity toEntity(AdReward adReward) {
		UserEntity userEntity = entityManager.getReference(UserEntity.class, adReward.getUserId());
		return AdRewardEntity.builder()
			.id(adReward.getAdRewardId())
			.user(userEntity)
			.usedAt(adReward.getUsedAt())
			.build();
	}
}