package tipitapi.drawmytodayimprovement.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.CreateJoinTicketEvent;
import tipitapi.drawmytodayimprovement.service.CreateTicketService;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateJoinTicketHandler {

	private final CreateTicketService createTicketService;

	@EventListener
	public void handle(CreateJoinTicketEvent event) {
		createTicketService.createJoinTickets(event.userId());
	}
}
