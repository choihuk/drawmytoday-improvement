package tipitapi.drawmytodayimprovement.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.domain.User;

@Repository
public interface UserRepository {
	List<User> findAllByEmail(String email);

	User save(User user);
}
