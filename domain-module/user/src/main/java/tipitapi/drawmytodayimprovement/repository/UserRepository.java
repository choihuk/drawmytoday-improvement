package tipitapi.drawmytodayimprovement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.User;

@Repository
public interface UserRepository {
	List<User> findAllByEmail(String email);

	User save(User user);

	Optional<User> findById(Long userId);
}
