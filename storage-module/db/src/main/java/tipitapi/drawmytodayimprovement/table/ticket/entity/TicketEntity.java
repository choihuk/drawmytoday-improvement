package tipitapi.drawmytodayimprovement.table.ticket.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.domain.TicketType;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TicketEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    private LocalDateTime usedAt;

    @Builder
    private TicketEntity(Long id, UserEntity user, TicketType ticketType, LocalDateTime usedAt) {
        this.id = id;
        this.user = user;
        this.ticketType = ticketType;
        this.usedAt = usedAt;
    }
}
