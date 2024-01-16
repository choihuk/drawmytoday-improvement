package tipitapi.drawmytodayimprovement.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Emotion;

@Repository
public interface EmotionRepository {

	Optional<Emotion> findById(Long diaryId);
}
