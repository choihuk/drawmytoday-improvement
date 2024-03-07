package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Image;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository {
    Image save(Image image);

    List<Image> findAll(Long diaryId);

    List<Image> findAllLatestSorted(Long diaryId);

    Optional<Image> findRecentByDiary(Long diaryId);

    List<Image> saveAll(List<Image> images);
}
