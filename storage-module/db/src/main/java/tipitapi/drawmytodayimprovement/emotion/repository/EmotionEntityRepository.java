package tipitapi.drawmytodayimprovement.emotion.repository;

import static tipitapi.drawmytodayimprovement.diary.entity.QDiaryEntity.*;
import static tipitapi.drawmytodayimprovement.emotion.entity.QEmotionEntity.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.emotion.mapper.EmotionMapper;
import tipitapi.drawmytodayimprovement.repository.EmotionRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmotionEntityRepository implements EmotionRepository {

	private final EmotionMapper emotionMapper;
	private final EmotionJpaRepository emotionJpaRepository;
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<Emotion> findById(Long emotionId) {
		return emotionJpaRepository.findById(emotionId)
			.map(emotionMapper::toDomain);
	}

	@Override
	public Optional<Emotion> findByDiaryId(Long diaryId) {
		return Optional.ofNullable(
			queryFactory.select(emotionEntity)
				.from(diaryEntity)
				.join(diaryEntity.emotion, emotionEntity)
				.where(diaryEntity.id.eq(diaryId))
				.fetchFirst()
		).map(emotionMapper::toDomain);

	}
}
