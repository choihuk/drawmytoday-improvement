package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.Auth;
import tipitapi.drawmytodayimprovement.repository.AuthRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateAuthService {

	private final AuthRepository authRepository;

	public void create(Long userId, String refreshToken) {
		authRepository.save(Auth.create(userId, refreshToken));
	}

}
