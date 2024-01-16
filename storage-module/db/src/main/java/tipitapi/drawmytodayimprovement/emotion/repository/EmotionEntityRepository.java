package tipitapi.drawmytodayimprovement.emotion.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.emotion.mapper.EmotionMapper;
import tipitapi.drawmytodayimprovement.repository.EmotionRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmotionEntityRepository implements EmotionRepository {

	private final EmotionJpaRepository emotionJpaRepository;
	private final EmotionMapper emotionMapper;

	@Override
	public Optional<Emotion> findById(Long emotionId) {
		return emotionJpaRepository.findById(emotionId)
			.map(emotionMapper::toDomain);
	}
}
