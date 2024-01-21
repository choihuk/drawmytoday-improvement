package tipitapi.drawmytodayimprovement.exception;

public class NotOwnerOfDiaryException extends BusinessException {

	public NotOwnerOfDiaryException() {
		super(DiaryErrorCode.DIARY_NOT_OWNER);
	}
}
