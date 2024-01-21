package tipitapi.drawmytodayimprovement.exception;

public enum TicketErrorCode implements ErrorCode {

	VALID_TICKET_NOT_EXISTS(404, "T001", "유효한 티켓이 존재하지 않습니다.");

	private final int status;
	private final String code;
	private final String message;

	TicketErrorCode(final int status, final String code, final String message) {
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
