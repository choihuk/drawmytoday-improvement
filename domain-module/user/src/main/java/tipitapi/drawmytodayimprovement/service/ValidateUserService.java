package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.User;
import tipitapi.drawmytodayimprovement.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.exception.UserNotFoundException;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidateUserService {

	private final UserRepository userRepository;

	public User validateByUserId(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new);
	}

	public User validateByEmailAndSocialCode(String email, SocialCode socialCode) {
		return userRepository.findAllByEmail(email).stream()
			.filter(user -> user.getSocialCode() == socialCode)
			.findFirst()
			.orElseThrow(UserNotFoundException::new);
	}
}
