package tipitapi.drawmytodayimprovement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Image;

@Repository
public interface ImageRepository {
	Image save(Image image);

	List<Image> findAll(Long diaryId);

	List<Image> findAllLatestSorted(Long diaryId);

	Optional<Image> findRecentByDiary(Long diaryId);
}
