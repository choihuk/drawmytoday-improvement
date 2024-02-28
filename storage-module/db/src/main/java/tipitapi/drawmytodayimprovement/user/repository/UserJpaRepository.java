package tipitapi.drawmytodayimprovement.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findAllByEmail(String email);
}
