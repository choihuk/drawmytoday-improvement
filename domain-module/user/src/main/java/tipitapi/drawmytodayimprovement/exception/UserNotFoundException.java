package tipitapi.drawmytodayimprovement.exception;

public class UserNotFoundException extends BusinessException {

	public UserNotFoundException() {
		super(UserErrorCode.USER_NOT_FOUND);
	}
}
