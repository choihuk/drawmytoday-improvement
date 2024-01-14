package tipitapi.drawmytodayimprovement.exception;

import tipitapi.drawmytodayimprovement.domain.exception.BusinessException;
import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
