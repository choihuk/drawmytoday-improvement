package tipitapi.drawmytodayimprovement.exception;

public interface ErrorCode {
	int getStatus();

	String getCode();

	String getMessage();
}
