package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.exception.ValidTicketNotExistsException;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class ValidateTicketService {

    private final TicketRepository ticketRepository;

    public Ticket validateAvailableTicketLeft(Long userId) {
        return ticketRepository.findValidTicket(userId)
                .orElseThrow(ValidTicketNotExistsException::new);
    }
}
