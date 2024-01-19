package tipitapi.drawmytodayimprovement.karlo.exception;

import tipitapi.drawmytodayimprovement.exception.ErrorCode;
import tipitapi.drawmytodayimprovement.exception.ImageGeneratorException;

public class ImageInputStreamFailException extends ImageGeneratorException {

	public ImageInputStreamFailException() {
		super(ErrorCode.IMAGE_INPUT_STREAM_FAIL);
	}

	public ImageInputStreamFailException(Throwable throwable) {
		super(ErrorCode.IMAGE_INPUT_STREAM_FAIL, throwable);
	}
}
