package tipitapi.drawmytodayimprovement.domain.auth.application.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.event.AfterRegisterUserEvent;
import tipitapi.drawmytodayimprovement.service.CreateAuthService;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final CreateAuthService createAuthService;

    @EventListener
    public void handle(AfterRegisterUserEvent event) {
        createAuthService.create(event.userId(), event.refreshToken());
    }
}
