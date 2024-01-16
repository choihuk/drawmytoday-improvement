package tipitapi.drawmytodayimprovement.event;

public record UseOldTicketEvent(Long userId) {
	public static UseOldTicketEvent of(Long userId) {
		return new UseOldTicketEvent(userId);
	}
}
