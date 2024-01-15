package tipitapi.drawmytodayimprovement.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdReward {

	private Long adRewardId;
	private LocalDateTime createdAt;
	private Long userId;
	private LocalDateTime usedAt;
}
