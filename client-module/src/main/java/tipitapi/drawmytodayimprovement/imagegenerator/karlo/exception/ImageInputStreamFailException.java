package tipitapi.drawmytodayimprovement.imagegenerator.karlo.exception;

import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;

public class ImageInputStreamFailException extends ImageGeneratorException {

	public ImageInputStreamFailException() {
		super(CommonErrorCode.IMAGE_INPUT_STREAM_FAIL);
	}

	public ImageInputStreamFailException(Throwable throwable) {
		super(CommonErrorCode.IMAGE_INPUT_STREAM_FAIL, throwable);
	}
}
