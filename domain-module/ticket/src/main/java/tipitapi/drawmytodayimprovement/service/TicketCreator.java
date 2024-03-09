package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TicketCreator {

    private static final int JOIN_TICKET_COUNT = 1;
    private final TicketRepository ticketRepository;

    public void createAndSaveJoinTickets(Long userId) {
        List<Ticket> tickets = IntStream.range(0, JOIN_TICKET_COUNT)
                .mapToObj(i -> Ticket.createJoinTicket(userId))
                .collect(Collectors.toList());
        ticketRepository.saveAll(tickets);
    }

    public void createAndSaveAdRewardTicket(Long userId) {
        ticketRepository.save(Ticket.createAdRewardTicket(userId));
    }

}
