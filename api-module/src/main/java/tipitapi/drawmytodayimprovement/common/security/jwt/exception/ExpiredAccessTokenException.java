package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

public class ExpiredAccessTokenException extends TokenException {

	public ExpiredAccessTokenException() {
		super(SecurityErrorCode.EXPIRED_JWT_ACCESS_TOKEN);
	}
}
