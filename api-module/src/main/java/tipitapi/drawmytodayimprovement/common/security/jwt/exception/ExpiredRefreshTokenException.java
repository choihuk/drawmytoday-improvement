package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

public class ExpiredRefreshTokenException extends TokenException {

	public ExpiredRefreshTokenException() {
		super(SecurityErrorCode.EXPIRED_JWT_REFRESH_TOKEN);
	}
}
