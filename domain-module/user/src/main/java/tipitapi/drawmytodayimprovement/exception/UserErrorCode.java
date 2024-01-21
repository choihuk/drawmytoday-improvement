package tipitapi.drawmytodayimprovement.exception;

public enum UserErrorCode implements ErrorCode {

	USER_NOT_FOUND(404, "U001", "회원을 찾을 수 없습니다."),
	USER_ALREADY_EXISTS(409, "U002", "이미 존재하는 유저입니다."),
	DUPLICATE_USER(400, "U003", "유저가 중복되었습니다."),
	USER_ALREADY_DRAW_DIARY(400, "U004", "이미 그림일기를 그린 유저입니다."),
	USER_ACCESS_DENIED(403, "U005", "접근할 수 있는 권한이 없습니다.");

	private final int status;
	private final String code;
	private final String message;

	UserErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
