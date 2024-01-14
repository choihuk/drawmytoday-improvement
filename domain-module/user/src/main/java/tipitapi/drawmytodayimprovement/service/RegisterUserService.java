package tipitapi.drawmytodayimprovement.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.domain.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.handler.event.CreateAuthEvent;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterUserService {

	private final UserRepository userRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	public User registerUser(String email, SocialCode socialCode, String refreshToken) {
		User user = userRepository.save(User.create(email, socialCode));
		applicationEventPublisher.publishEvent(new CreateAuthEvent(user.getUserId(), refreshToken));
		return user;
	}

	// @Transactional
	// public User registerUser(String email, SocialCode socialCode, String refreshToken) {
	// 	User user = userRepository.save(User.create(email, socialCode));
	// 	authRepository.save(Auth.create(user, refreshToken));
	// 	ticketService.createTicketByJoin(user);
	// 	return user;
	// }

}
