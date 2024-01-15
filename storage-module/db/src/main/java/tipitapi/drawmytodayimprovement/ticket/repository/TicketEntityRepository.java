package tipitapi.drawmytodayimprovement.ticket.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;
import tipitapi.drawmytodayimprovement.ticket.mapper.TicketMapper;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class TicketEntityRepository implements TicketRepository {

	private final TicketJpaRepository ticketJpaRepository;
	private final TicketMapper ticketMapper;

	@Override
	public List<Ticket> saveAll(List<Ticket> tickets) {
		return ticketJpaRepository.saveAll(
				tickets.stream()
					.map(ticketMapper::toEntity)
					.toList()
			).stream()
			.map(ticketMapper::toDomain)
			.toList();
	}
}
