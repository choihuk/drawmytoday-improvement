package tipitapi.drawmytodayimprovement.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTicketService {

	private static final int JOIN_TICKET_COUNT = 1;
	private final TicketRepository ticketRepository;

	public void createJoinTickets(Long userId) {
		List<Ticket> tickets = IntStream.range(0, JOIN_TICKET_COUNT)
			.mapToObj(i -> Ticket.createJoinTicket(userId))
			.collect(Collectors.toList());
		ticketRepository.saveAll(tickets);
	}

}
