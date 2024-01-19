package tipitapi.drawmytodayimprovement.exception;

public class DiaryNotFoundException extends BusinessException {

	public DiaryNotFoundException() {
		super(ErrorCode.DIARY_NOT_FOUND);
	}
}
