package tipitapi.drawmytodayimprovement.event;

public record AfterRegisterUserEvent(Long userId, String refreshToken) {

	public static AfterRegisterUserEvent of(Long userId, String refreshToken) {
		return new AfterRegisterUserEvent(userId, refreshToken);
	}
}
