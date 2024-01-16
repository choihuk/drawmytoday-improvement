package tipitapi.drawmytodayimprovement.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.UseOldTicketEvent;
import tipitapi.drawmytodayimprovement.exception.ValidTicketNotExistsException;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Component
@Transactional
@RequiredArgsConstructor
public class UseTicketHandler {

	private final TicketRepository ticketRepository;

	@EventListener
	public void handle(UseOldTicketEvent event) {
		ticketRepository.findValidTicket(event.userId())
			.orElseThrow(ValidTicketNotExistsException::new)
			.useTicket();
	}
}
