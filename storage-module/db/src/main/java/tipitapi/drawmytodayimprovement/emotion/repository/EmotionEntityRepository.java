package tipitapi.drawmytodayimprovement.emotion.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.emotion.mapper.EmotionMapper;
import tipitapi.drawmytodayimprovement.repository.EmotionRepository;

import java.util.Optional;

import static tipitapi.drawmytodayimprovement.diary.entity.QDiaryEntity.diaryEntity;
import static tipitapi.drawmytodayimprovement.emotion.entity.QEmotionEntity.emotionEntity;

@Repository
@Transactional
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
