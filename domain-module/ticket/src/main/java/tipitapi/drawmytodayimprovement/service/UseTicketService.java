package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Service
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
