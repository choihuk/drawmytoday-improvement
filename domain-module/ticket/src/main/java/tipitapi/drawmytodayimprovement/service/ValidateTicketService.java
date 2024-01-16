package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.exception.ValidTicketNotExistsException;
import tipitapi.drawmytodayimprovement.repository.TicketRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidateTicketService {

	private final TicketRepository ticketRepository;

	public void validateDrawable(Long userId) {
		ticketRepository.findValidTicket(userId)
			.orElseThrow(ValidTicketNotExistsException::new);
	}
}
