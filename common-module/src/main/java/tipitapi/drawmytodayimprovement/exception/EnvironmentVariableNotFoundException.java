package tipitapi.drawmytodayimprovement.exception;

public class EnvironmentVariableNotFoundException extends BusinessException {
	public EnvironmentVariableNotFoundException() {
		super(CommonErrorCode.INTERNAL_SERVER_ERROR);
	}
}
