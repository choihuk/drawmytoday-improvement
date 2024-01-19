package tipitapi.drawmytodayimprovement.exception;

public class ValidTicketNotExistsException extends BusinessException {

	public ValidTicketNotExistsException() {
		super(ErrorCode.VALID_TICKET_NOT_EXISTS);
	}
}
