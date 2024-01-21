package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UseTicketService {

	private final ValidateTicketService validateTicketService;
	private final TicketRepository ticketRepository;

	public void useTicket(Long userId) {
		Ticket ticket = validateTicketService.validateAvailableTicketLeft(userId);
		ticket.useTicket();
		ticketRepository.save(ticket);
	}
}
