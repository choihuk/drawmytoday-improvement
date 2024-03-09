package tipitapi.drawmytodayimprovement.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ticket {

    private Long ticketId;
    private LocalDateTime createdAt;
    private Long userId;
    private TicketType ticketType;
    private LocalDateTime usedAt;

    public static Ticket createJoinTicket(Long userId) {
        return Ticket.builder()
                .userId(userId)
                .ticketType(TicketType.JOIN)
                .build();
    }

    public static Ticket createAdRewardTicket(Long userId) {
        return Ticket.builder()
                .userId(userId)
                .ticketType(TicketType.AD_REWARD)
                .build();
    }

    public void useTicket() {
        this.usedAt = LocalDateTime.now();
    }
}
