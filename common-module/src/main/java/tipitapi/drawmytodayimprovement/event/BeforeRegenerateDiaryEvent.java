package tipitapi.drawmytodayimprovement.event;

public record BeforeRegenerateDiaryEvent(Long userId) {

    public static BeforeRegenerateDiaryEvent of(Long userId) {
        return new BeforeRegenerateDiaryEvent(userId);
    }
}
