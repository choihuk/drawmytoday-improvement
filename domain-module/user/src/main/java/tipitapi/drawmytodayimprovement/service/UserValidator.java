package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.exception.UserNotFoundException;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserValidator {

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
