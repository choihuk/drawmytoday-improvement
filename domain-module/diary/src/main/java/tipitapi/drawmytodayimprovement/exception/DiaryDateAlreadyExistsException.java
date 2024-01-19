package tipitapi.drawmytodayimprovement.exception;

public class DiaryDateAlreadyExistsException extends BusinessException {

	public DiaryDateAlreadyExistsException() {
		super(ErrorCode.DIARY_DATE_ALREADY_EXISTS);
	}
}
