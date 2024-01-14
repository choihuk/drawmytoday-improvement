package tipitapi.drawmytodayimprovement.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Auth {

	private Long authId;

	private Long userId;

	private String refreshToken;
}
