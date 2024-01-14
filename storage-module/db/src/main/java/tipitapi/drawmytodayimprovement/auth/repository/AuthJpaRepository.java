package tipitapi.drawmytodayimprovement.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.auth.entity.AuthEntity;

interface AuthJpaRepository extends JpaRepository<AuthEntity, Long> {
}
