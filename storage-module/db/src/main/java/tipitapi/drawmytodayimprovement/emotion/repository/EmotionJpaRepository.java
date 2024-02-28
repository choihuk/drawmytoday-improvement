package tipitapi.drawmytodayimprovement.emotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.emotion.entity.EmotionEntity;

public interface EmotionJpaRepository extends JpaRepository<EmotionEntity, Long> {
}
