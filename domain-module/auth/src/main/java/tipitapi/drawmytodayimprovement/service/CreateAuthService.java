package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Auth;
import tipitapi.drawmytodayimprovement.repository.AuthRepository;

@Service
@RequiredArgsConstructor
public class CreateAuthService {

    private final AuthRepository authRepository;

    public void create(Long userId, String refreshToken) {
        authRepository.save(Auth.create(userId, refreshToken));
    }

}
