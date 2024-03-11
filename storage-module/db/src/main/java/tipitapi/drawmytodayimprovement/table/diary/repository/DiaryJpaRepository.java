package tipitapi.drawmytodayimprovement.table.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.table.diary.entity.DiaryEntity;

import java.util.Optional;

public interface DiaryJpaRepository extends JpaRepository<DiaryEntity, Long> {
    Optional<DiaryEntity> findFirstByUserIdOrderByCreatedAtDesc(Long userId);

    // boolean existsByUserIdAndDiaryDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}