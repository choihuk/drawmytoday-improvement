package tipitapi.drawmytodayimprovement.event;

public record CreateAuthEvent(Long userId, String refreshToken) {

	public static CreateAuthEvent of(Long userId, String refreshToken) {
		return new CreateAuthEvent(userId, refreshToken);
	}
}
