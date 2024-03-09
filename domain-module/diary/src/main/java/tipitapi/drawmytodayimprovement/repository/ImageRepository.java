package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.dto.ImageForMonitoring;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository {
    Image save(Image image);

    List<Image> findAll(Long diaryId);

    List<Image> findAllLatestSorted(Long diaryId);

    Optional<Image> findRecentByDiary(Long diaryId);

    List<Image> saveAll(List<Image> images);

    PageResponse<ImageForMonitoring> getImagesForMonitoring(PageableRequest pageableRequest, Long emotionId, boolean withTest);
}
