package tipitapi.drawmytodayimprovement.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.AfterCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.event.AfterRegisterUserEvent;
import tipitapi.drawmytodayimprovement.event.BeforeCreateDiaryEvent;
import tipitapi.drawmytodayimprovement.service.CreateTicketService;
import tipitapi.drawmytodayimprovement.service.UseTicketService;
import tipitapi.drawmytodayimprovement.service.ValidateTicketService;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
class TicketHandler {

	private final ValidateTicketService validateTicketService;
	private final CreateTicketService createTicketService;
	private final UseTicketService useTicketService;

	@EventListener
	public void handle(BeforeCreateDiaryEvent event) {
		validateTicketService.validateAvailableTicketLeft(event.userId());
	}

	@EventListener
	@Transactional
	public void handle(AfterCreateDiaryEvent event) {
		useTicketService.useTicket(event.userId());
	}

	@EventListener
	@Transactional
	public void handle(AfterRegisterUserEvent event) {
		createTicketService.createJoinTickets(event.userId());
	}
}
