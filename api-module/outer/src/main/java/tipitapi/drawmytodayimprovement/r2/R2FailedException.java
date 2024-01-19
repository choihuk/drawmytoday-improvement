package tipitapi.drawmytodayimprovement.r2;

import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.ErrorCode;

public class R2FailedException extends BusinessException {

	public R2FailedException(Throwable throwable) {
		super(ErrorCode.R2_FAILED, throwable);
	}
}
