package tipitapi.drawmytodayimprovement.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.domain.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.event.CreateAuthEvent;
import tipitapi.drawmytodayimprovement.event.CreateJoinTicketEvent;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterUserService {

	private final UserRepository userRepository;
	private final ApplicationEventPublisher applicationEventPublisher;


	@Transactional
	public User registerUser(String email, SocialCode socialCode, String refreshToken) {
		User user = userRepository.save(User.create(email, socialCode));
		applicationEventPublisher.publishEvent(CreateAuthEvent.of(user.getUserId(), refreshToken));
		applicationEventPublisher.publishEvent(CreateJoinTicketEvent.of(user.getUserId()));
		return user;
	}

}
