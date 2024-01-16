package tipitapi.drawmytodayimprovement.event;

public record ValidateDrawableEvent(Long userId) {

	public static ValidateDrawableEvent of(Long userId) {
		return new ValidateDrawableEvent(userId);
	}
}
