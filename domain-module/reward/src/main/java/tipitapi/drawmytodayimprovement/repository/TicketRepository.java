package tipitapi.drawmytodayimprovement.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.domain.Ticket;

@Repository
public interface TicketRepository {
	List<Ticket> saveAll(List<Ticket> tickets);
}
