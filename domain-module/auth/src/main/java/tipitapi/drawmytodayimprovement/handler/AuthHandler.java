package tipitapi.drawmytodayimprovement.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.AfterRegisterUserEvent;
import tipitapi.drawmytodayimprovement.service.CreateAuthService;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthHandler {

	private final CreateAuthService createAuthService;

	@EventListener
	@Transactional
	public void handle(AfterRegisterUserEvent event) {
		createAuthService.create(event.userId(), event.refreshToken());
	}
}
