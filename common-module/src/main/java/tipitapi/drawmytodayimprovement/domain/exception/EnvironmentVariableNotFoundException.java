package tipitapi.drawmytodayimprovement.domain.exception;

public class EnvironmentVariableNotFoundException extends BusinessException{
	public EnvironmentVariableNotFoundException() {
		super(ErrorCode.INTERNAL_SERVER_ERROR);
	}
}
