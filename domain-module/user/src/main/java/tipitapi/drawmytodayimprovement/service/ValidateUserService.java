package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.domain.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.exception.UserNotFoundException;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ValidateUserService {

	private final UserRepository userRepository;

	public User validateRegisteredUserByEmail(String email, SocialCode socialCode) {
		return userRepository.findAllByEmail(email).stream()
			.filter(user -> user.getSocialCode() == socialCode)
			.findFirst()
			.orElseThrow(UserNotFoundException::new);
	}
}
