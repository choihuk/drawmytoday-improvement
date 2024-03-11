package tipitapi.drawmytodayimprovement.table.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.table.auth.entity.AuthEntity;

interface AuthJpaRepository extends JpaRepository<AuthEntity, Long> {
}
