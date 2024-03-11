package tipitapi.drawmytodayimprovement.table.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.table.image.entity.ImageEntity;

import java.util.List;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findAllByDiaryId(Long diaryId);

    List<ImageEntity> findAllByDiaryIdOrderByCreatedAtDesc(Long diaryId);
}
