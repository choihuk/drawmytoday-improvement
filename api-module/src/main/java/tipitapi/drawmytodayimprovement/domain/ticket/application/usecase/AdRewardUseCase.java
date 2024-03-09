package tipitapi.drawmytodayimprovement.domain.ticket.application.usecase;

import org.springframework.transaction.annotation.Transactional;

public interface AdRewardUseCase {
    @Transactional
    void createAdReward(Long userId);
}
