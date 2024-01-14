package tipitapi.drawmytodayimprovement.exception;

import tipitapi.drawmytodayimprovement.domain.exception.BusinessException;
import tipitapi.drawmytodayimprovement.domain.exception.ErrorCode;

public class DiaryNotFoundException extends BusinessException {

    public DiaryNotFoundException() {
        super(ErrorCode.DIARY_NOT_FOUND);
    }
}
