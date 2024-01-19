package tipitapi.drawmytodayimprovement.ticket.repository;

import static tipitapi.drawmytodayimprovement.ticket.entity.QTicketEntity.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;
import tipitapi.drawmytodayimprovement.ticket.mapper.TicketMapper;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class TicketEntityRepository implements TicketRepository {

	private final TicketJpaRepository ticketJpaRepository;
	private final TicketMapper ticketMapper;
	private final JPAQueryFactory queryFactory;

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

	@Override
	public Optional<Ticket> findValidTicket(Long userId) {
		return Optional.ofNullable(
			queryFactory.selectFrom(ticketEntity)
				.where(ticketEntity.usedAt.isNull()
					.and(ticketEntity.user.id.eq(userId)))
				.fetchFirst()
		).map(ticketMapper::toDomain);
	}
}
