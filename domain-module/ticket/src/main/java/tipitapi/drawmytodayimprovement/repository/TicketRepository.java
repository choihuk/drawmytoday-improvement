package tipitapi.drawmytodayimprovement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Ticket;

@Repository
public interface TicketRepository {
	List<Ticket> saveAll(List<Ticket> tickets);

	Optional<Ticket> findValidTicket(Long userId);

	Ticket save(Ticket ticket);
}
