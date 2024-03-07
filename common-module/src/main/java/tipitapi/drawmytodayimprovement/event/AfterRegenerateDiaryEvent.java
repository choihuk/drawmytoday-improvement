package tipitapi.drawmytodayimprovement.event;

public record AfterRegenerateDiaryEvent(Long userId) {

    public static AfterRegenerateDiaryEvent of(Long userId) {
        return new AfterRegenerateDiaryEvent(userId);
    }
}
