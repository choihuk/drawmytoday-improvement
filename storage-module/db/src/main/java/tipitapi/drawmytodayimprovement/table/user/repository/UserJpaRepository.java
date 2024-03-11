package tipitapi.drawmytodayimprovement.table.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByEmail(String email);
}
