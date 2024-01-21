package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

public class InvalidTokenException extends TokenException {

	public InvalidTokenException(Throwable e) {
		super(SecurityErrorCode.INVALID_TOKEN, e);
	}

	public InvalidTokenException() {
		super(SecurityErrorCode.INVALID_TOKEN);
	}
}
