package tipitapi.drawmytodayimprovement.exception;

import tipitapi.drawmytodayimprovement.domain.exception.BusinessException;
import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class NotOwnerOfDiaryException extends BusinessException {

    public NotOwnerOfDiaryException() {
        super(ErrorCode.DIARY_NOT_OWNER);
    }
}
