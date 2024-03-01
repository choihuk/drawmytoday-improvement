package tipitapi.drawmytodayimprovement.ticket.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.Ticket;
import tipitapi.drawmytodayimprovement.ticket.entity.TicketEntity;
import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final EntityManager entityManager;

    public Ticket toDomain(TicketEntity entity) {
        return Ticket.builder()
                .ticketId(entity.getId())
                .createdAt(entity.getCreatedAt())
                .userId(entity.getUser().getId())
                .ticketType(entity.getTicketType())
                .usedAt(entity.getUsedAt())
                .build();
    }

    public TicketEntity toEntity(Ticket ticket) {
        UserEntity userEntity = entityManager.getReference(UserEntity.class, ticket.getUserId());
        return TicketEntity.builder()
                .id(ticket.getTicketId())
                .user(userEntity)
                .ticketType(ticket.getTicketType())
                .usedAt(ticket.getUsedAt())
                .build();
    }
}
