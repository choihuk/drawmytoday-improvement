package tipitapi.drawmytodayimprovement.exception;

public class DiaryDateAlreadyExistsException extends BusinessException {

	public DiaryDateAlreadyExistsException() {
		super(DiaryErrorCode.DIARY_DATE_ALREADY_EXISTS);
	}
}
