package tipitapi.drawmytodayimprovement.table.emotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.table.emotion.entity.EmotionEntity;

import java.util.List;

public interface EmotionJpaRepository extends JpaRepository<EmotionEntity, Long> {
    List<EmotionEntity> findAllByIsActiveTrueOrderByIdAsc();
}
