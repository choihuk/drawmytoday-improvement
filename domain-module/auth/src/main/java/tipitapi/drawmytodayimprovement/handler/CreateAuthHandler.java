package tipitapi.drawmytodayimprovement.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.CreateAuthEvent;
import tipitapi.drawmytodayimprovement.service.CreateAuthService;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateAuthHandler {

	private final CreateAuthService createAuthService;

	@EventListener
	public void handle(CreateAuthEvent event) {
		createAuthService.create(event.userId(), event.refreshToken());
	}
}
