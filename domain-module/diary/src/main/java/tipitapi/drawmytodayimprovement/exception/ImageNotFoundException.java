package tipitapi.drawmytodayimprovement.exception;

public class ImageNotFoundException extends BusinessException {

	public ImageNotFoundException() {
		super(ErrorCode.IMAGE_NOT_FOUND);
	}
}
