package tipitapi.drawmytodayimprovement.storage.r2;

import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;

public class R2FailedException extends BusinessException {

	public R2FailedException(Throwable throwable) {
		super(CommonErrorCode.R2_FAILED, throwable);
	}
}
