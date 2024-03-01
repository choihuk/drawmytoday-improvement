package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.event.AfterRegisterUserEvent;
import tipitapi.drawmytodayimprovement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterUserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public User registerUser(String email, SocialCode socialCode, String refreshToken) {
        User user = userRepository.save(User.create(email, socialCode));
        // create Auth Entity and Ticket Entity
        applicationEventPublisher.publishEvent(AfterRegisterUserEvent.of(user.getUserId(), refreshToken));
        return user;
    }

}
