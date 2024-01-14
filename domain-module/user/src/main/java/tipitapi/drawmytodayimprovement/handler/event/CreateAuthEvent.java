package tipitapi.drawmytodayimprovement.handler.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAuthEvent {
	private final Long userId;
	private final String refreshToken;
}
