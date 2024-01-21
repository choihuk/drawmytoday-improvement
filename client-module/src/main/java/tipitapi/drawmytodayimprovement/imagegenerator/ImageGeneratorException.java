package tipitapi.drawmytodayimprovement.imagegenerator;

import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;

public abstract class ImageGeneratorException extends BusinessException {
	public ImageGeneratorException(CommonErrorCode commonErrorCode) {
		super(commonErrorCode);
	}

	public ImageGeneratorException(CommonErrorCode commonErrorCode, Throwable throwable) {
		super(commonErrorCode, throwable);
	}
}
