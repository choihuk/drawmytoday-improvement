package tipitapi.drawmytodayimprovement.table.adreward.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.AdReward;
import tipitapi.drawmytodayimprovement.table.adreward.entity.AdRewardEntity;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.EntityManager;

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
