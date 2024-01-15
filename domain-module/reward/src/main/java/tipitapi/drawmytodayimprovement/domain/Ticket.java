package tipitapi.drawmytodayimprovement.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import tipitapi.drawmytodayimprovement.domain.enumeration.TicketType;

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
}
