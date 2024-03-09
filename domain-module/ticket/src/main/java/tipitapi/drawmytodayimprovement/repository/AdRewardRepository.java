package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.AdReward;

@Repository
public interface AdRewardRepository {

    AdReward save(AdReward adReward);
}
