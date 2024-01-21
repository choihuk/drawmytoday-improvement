package tipitapi.drawmytodayimprovement.event;

public record BeforeCreateDiaryEvent(Long userId) {

	public static BeforeCreateDiaryEvent of(Long userId) {
		return new BeforeCreateDiaryEvent(userId);
	}
}
