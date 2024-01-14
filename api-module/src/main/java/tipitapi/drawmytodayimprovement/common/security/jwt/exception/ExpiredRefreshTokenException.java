package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class ExpiredRefreshTokenException extends TokenException {

    public ExpiredRefreshTokenException() {
        super(ErrorCode.EXPIRED_JWT_REFRESH_TOKEN);
    }
}
