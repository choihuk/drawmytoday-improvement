package tipitapi.drawmytodayimprovement.exception;

public class DiaryNotFoundException extends BusinessException {

	public DiaryNotFoundException() {
		super(DiaryErrorCode.DIARY_NOT_FOUND);
	}
}
