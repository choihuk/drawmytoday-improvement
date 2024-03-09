package tipitapi.drawmytodayimprovement.adreward.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.adreward.mapper.AdRewardMapper;
import tipitapi.drawmytodayimprovement.domain.AdReward;
import tipitapi.drawmytodayimprovement.repository.AdRewardRepository;

@Repository
@Transactional
@RequiredArgsConstructor
class AdRewardEntityRepository implements AdRewardRepository {

    private final AdRewardJapRepository adRewardJapRepository;
    private final AdRewardMapper adRewardMapper;

    @Override
    public AdReward save(AdReward adReward) {
        return adRewardMapper.toDomain(adRewardJapRepository.save(adRewardMapper.toEntity(adReward)));
    }
}
