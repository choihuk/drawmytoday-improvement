package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository {
    List<Ticket> saveAll(List<Ticket> tickets);

    Optional<Ticket> findAvailableTicket(Long userId);

    Ticket save(Ticket ticket);
}
