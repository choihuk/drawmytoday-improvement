package tipitapi.drawmytodayimprovement.exception;

public class EmotionNotFoundException extends BusinessException {

    public EmotionNotFoundException() {
        super(ErrorCode.EMOTION_NOT_FOUND);
    }
}
