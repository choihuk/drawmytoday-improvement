package tipitapi.drawmytodayimprovement.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdReward {

    private Long adRewardId;
    private LocalDateTime createdAt;
    private Long userId;
    private LocalDateTime usedAt;
}
