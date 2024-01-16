package tipitapi.drawmytodayimprovement.ticket.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.enumeration.TicketType;
import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

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
