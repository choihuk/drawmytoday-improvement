package tipitapi.drawmytodayimprovement.exception;

public class ImageNotFoundException extends BusinessException {

	public ImageNotFoundException() {
		super(DiaryErrorCode.IMAGE_NOT_FOUND);
	}
}
