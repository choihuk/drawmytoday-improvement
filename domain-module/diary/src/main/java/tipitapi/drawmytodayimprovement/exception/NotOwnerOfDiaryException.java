package tipitapi.drawmytodayimprovement.exception;

public class NotOwnerOfDiaryException extends BusinessException {

	public NotOwnerOfDiaryException() {
		super(ErrorCode.DIARY_NOT_OWNER);
	}
}
