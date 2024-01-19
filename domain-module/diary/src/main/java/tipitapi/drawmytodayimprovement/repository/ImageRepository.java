package tipitapi.drawmytodayimprovement.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Image;

@Repository
public interface ImageRepository {
	Image save(Image image);

	List<Image> findAll(Long diaryId);

	List<Image> findAllLatestSorted(Long diaryId);
}
