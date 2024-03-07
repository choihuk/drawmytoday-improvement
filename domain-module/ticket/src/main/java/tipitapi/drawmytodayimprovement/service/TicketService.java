package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketValidator ticketValidator;
    private final TicketRepository ticketRepository;

    public void useTicket(Long userId) {
        Ticket ticket = ticketValidator.validateAvailableTicketLeft(userId);
        ticket.useTicket();
        ticketRepository.save(ticket);
    }

    public Optional<Ticket> findAvailableTicket(Long userId) {
        return ticketRepository.findAvailableTicket(userId);
    }
}
