package tipitapi.drawmytodayimprovement.event;

public record AfterCreateDiaryEvent(Long userId) {
	public static AfterCreateDiaryEvent of(Long userId) {
		return new AfterCreateDiaryEvent(userId);
	}
}
