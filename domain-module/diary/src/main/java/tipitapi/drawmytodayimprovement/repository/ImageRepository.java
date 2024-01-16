package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Image;

@Repository
public interface ImageRepository {
	Image save(Image image);
}
