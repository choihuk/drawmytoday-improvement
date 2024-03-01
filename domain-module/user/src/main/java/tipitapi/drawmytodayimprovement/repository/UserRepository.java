package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    List<User> findAllByEmail(String email);

    User save(User user);

    Optional<User> findById(Long userId);
}
