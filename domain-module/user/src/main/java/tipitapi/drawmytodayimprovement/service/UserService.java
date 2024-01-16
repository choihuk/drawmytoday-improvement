package tipitapi.drawmytodayimprovement.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.event.UseOldTicketEvent;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	@Transactional
	public void useTicket(Long userId) {
		applicationEventPublisher.publishEvent(UseOldTicketEvent.of(userId));
	}
}
