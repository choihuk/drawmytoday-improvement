package tipitapi.drawmytodayimprovement.table.ticket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;
import tipitapi.drawmytodayimprovement.table.ticket.mapper.TicketMapper;

import java.util.List;
import java.util.Optional;

import static tipitapi.drawmytodayimprovement.table.ticket.entity.QTicketEntity.ticketEntity;

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
    public Optional<Ticket> findAvailableTicket(Long userId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(ticketEntity)
                        .where(ticketEntity.usedAt.isNull()
                                .and(ticketEntity.user.id.eq(userId)))
                        .fetchFirst()
        ).map(ticketMapper::toDomain);
    }

    @Override
    @Transactional
    public Ticket save(Ticket ticket) {
        return ticketMapper.toDomain(ticketJpaRepository.save(ticketMapper.toEntity(ticket)));
    }
}
