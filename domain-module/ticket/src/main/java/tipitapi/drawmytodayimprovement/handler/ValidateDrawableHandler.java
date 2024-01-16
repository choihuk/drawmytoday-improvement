package tipitapi.drawmytodayimprovement.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.ValidateDrawableEvent;
import tipitapi.drawmytodayimprovement.service.ValidateTicketService;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidateDrawableHandler {

	private final ValidateTicketService validateTicketService;

	@EventListener
	public void handle(ValidateDrawableEvent event) {
		validateTicketService.validateDrawable(event.userId());
	}
}
