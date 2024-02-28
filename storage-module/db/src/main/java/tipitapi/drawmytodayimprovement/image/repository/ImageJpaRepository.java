package tipitapi.drawmytodayimprovement.image.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.image.entity.ImageEntity;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
	List<ImageEntity> findAllByDiaryId(Long diaryId);

	List<ImageEntity> findAllByDiaryIdOrderByCreatedAtDesc(Long diaryId);
}
