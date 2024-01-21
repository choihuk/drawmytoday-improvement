package tipitapi.drawmytodayimprovement.exception;

public class ValidTicketNotExistsException extends BusinessException {

	public ValidTicketNotExistsException() {
		super(TicketErrorCode.VALID_TICKET_NOT_EXISTS);
	}
}
