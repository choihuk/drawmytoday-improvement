package tipitapi.drawmytodayimprovement.common.security.jwt.exception;

import tipitapi.drawmytodayimprovement.exception.ErrorCode;

public enum SecurityErrorCode implements ErrorCode {

	AUTHORITY_NOT_FOUND(404, "S001", "유저 권한이 없습니다."),
	INVALID_TOKEN(400, "S002", "유효하지 않은 토큰입니다."),
	JWT_ACCESS_TOKEN_NOT_FOUND(404, "S003", "jwt access token이 없습니다."),
	JWT_REFRESH_TOKEN_NOT_FOUND(404, "S004", "jwt refresh token이 없습니다."),
	EXPIRED_JWT_ACCESS_TOKEN(400, "S005", "jwt access token이 만료되었습니다."),
	EXPIRED_JWT_REFRESH_TOKEN(400, "S006", "jwt refresh token이 만료되었습니다."),
	AUTH_CODE_NOT_FOUND(404, "S007", "authorization header가 비었습니다."),
	JWT_TOKEN_NOT_FOUND(404, "S008", "jwt token이 없습니다.");

	private final int status;
	private final String code;
	private final String message;

	SecurityErrorCode(final int status, final String code, final String message) {
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
