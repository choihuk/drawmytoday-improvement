package tipitapi.drawmytodayimprovement.adreward.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.adreward.mapper.AdRewardMapper;
import tipitapi.drawmytodayimprovement.repository.AdRewardRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class AdRewardEntityRepository implements AdRewardRepository {

	private final AdRewardJapRepository adRewardJapRepository;
	private final AdRewardMapper adRewardMapper;


}
