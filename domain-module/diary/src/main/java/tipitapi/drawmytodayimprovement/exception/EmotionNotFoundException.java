package tipitapi.drawmytodayimprovement.exception;

public class EmotionNotFoundException extends BusinessException {

	public EmotionNotFoundException() {
		super(DiaryErrorCode.EMOTION_NOT_FOUND);
	}
}
