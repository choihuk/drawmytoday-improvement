package tipitapi.drawmytodayimprovement.imagegenerator.karlo.exception;

import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;

public class KarloRequestFailException extends ImageGeneratorException {

	public KarloRequestFailException() {
		super(CommonErrorCode.KARLO_REQUEST_FAIL);
	}

	public KarloRequestFailException(Throwable throwable) {
		super(CommonErrorCode.KARLO_REQUEST_FAIL, throwable);
	}
}
