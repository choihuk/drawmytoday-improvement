package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

import tipitapi.drawmytodayimprovement.domain.exception.BusinessException;
import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class TokenException extends BusinessException {

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TokenException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
