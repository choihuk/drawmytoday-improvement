package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Emotion;

import java.util.Optional;

@Repository
public interface EmotionRepository {

    Optional<Emotion> findById(Long diaryId);

    Optional<Emotion> findByDiaryId(Long diaryId);
}
