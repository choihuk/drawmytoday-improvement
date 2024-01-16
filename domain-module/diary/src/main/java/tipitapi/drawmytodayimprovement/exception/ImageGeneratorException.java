package tipitapi.drawmytodayimprovement.exception;

public abstract class ImageGeneratorException extends BusinessException {
	public ImageGeneratorException(ErrorCode errorCode) {
		super(errorCode);
	}

	public ImageGeneratorException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}
}
