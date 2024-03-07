package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.exception.ValidTicketNotExistsException;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketValidator {

    private final TicketRepository ticketRepository;

    public Ticket validateAvailableTicketLeft(Long userId) {
        return ticketRepository.findAvailableTicket(userId)
                .orElseThrow(ValidTicketNotExistsException::new);
    }
}
