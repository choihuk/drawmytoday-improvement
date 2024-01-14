package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class TokenNotFoundException extends TokenException {

    public TokenNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TokenNotFoundException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
