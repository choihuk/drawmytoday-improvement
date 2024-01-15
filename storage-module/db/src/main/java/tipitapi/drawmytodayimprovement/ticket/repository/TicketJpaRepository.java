package tipitapi.drawmytodayimprovement.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.ticket.entity.TicketEntity;

interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {
}
