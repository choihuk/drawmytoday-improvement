package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.SocialCode;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.domain.UserRole;
import tipitapi.drawmytodayimprovement.exception.UserAccessDeniedException;
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

    public User validateAdmin(Long userId) {
        User user = validateByUserId(userId);
        if (user.getUserRole() != UserRole.ADMIN) {
            throw new UserAccessDeniedException();
        }
        return user;
    }
}
