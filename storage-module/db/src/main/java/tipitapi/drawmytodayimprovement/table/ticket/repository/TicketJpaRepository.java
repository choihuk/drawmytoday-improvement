package tipitapi.drawmytodayimprovement.table.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.table.ticket.entity.TicketEntity;

interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {
}
