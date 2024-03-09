package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.AdReward;
import tipitapi.drawmytodayimprovement.repository.AdRewardRepository;

@Service
@RequiredArgsConstructor
public class AdRewardCreator {

    private final AdRewardRepository adRewardRepository;

    public AdReward createAndSaveAdReward(Long userId) {
        return adRewardRepository.save(AdReward.create(userId));
    }
}
