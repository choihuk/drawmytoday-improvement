package tipitapi.drawmytodayimprovement.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;

public interface DiaryJpaRepository extends JpaRepository<DiaryEntity, Long> {

	// boolean existsByUserIdAndDiaryDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}