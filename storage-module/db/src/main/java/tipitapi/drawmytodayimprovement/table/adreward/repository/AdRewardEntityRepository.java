package tipitapi.drawmytodayimprovement.table.adreward.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.AdReward;
import tipitapi.drawmytodayimprovement.repository.AdRewardRepository;
import tipitapi.drawmytodayimprovement.table.adreward.mapper.AdRewardMapper;

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
