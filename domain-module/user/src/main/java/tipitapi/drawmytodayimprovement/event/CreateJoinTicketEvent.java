package tipitapi.drawmytodayimprovement.event;

public record CreateJoinTicketEvent(Long userId) {

	public static CreateJoinTicketEvent of(Long userId) {
		return new CreateJoinTicketEvent(userId);
	}
}
