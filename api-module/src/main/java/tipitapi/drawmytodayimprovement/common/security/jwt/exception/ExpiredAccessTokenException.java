package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class ExpiredAccessTokenException extends TokenException {

    public ExpiredAccessTokenException() {
        super(ErrorCode.EXPIRED_JWT_ACCESS_TOKEN);
    }
}
