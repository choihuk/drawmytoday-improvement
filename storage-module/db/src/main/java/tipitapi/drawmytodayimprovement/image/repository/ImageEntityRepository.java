package tipitapi.drawmytodayimprovement.image.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.image.mapper.ImageMapper;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

import static tipitapi.drawmytodayimprovement.image.entity.QImageEntity.imageEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class ImageEntityRepository implements ImageRepository {

    private final ImageJpaRepository imageJpaRepository;
    private final ImageMapper imageMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public Image save(Image image) {
        return imageMapper.toDomain(imageJpaRepository.save(imageMapper.toEntity(image)));
    }

    @Override
    public List<Image> findAll(Long diaryId) {
        return imageJpaRepository.findAllByDiaryId(diaryId)
                .stream()
                .map(imageMapper::toDomain)
                .toList();
    }

    @Override
    public List<Image> findAllLatestSorted(Long diaryId) {
        return imageJpaRepository.findAllByDiaryIdOrderByCreatedAtDesc(diaryId)
                .stream()
                .map(imageMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Image> findRecentByDiary(Long diaryId) {
        return Optional.ofNullable(queryFactory
                        .selectFrom(imageEntity)
                        .where(imageEntity.diary.id.eq(diaryId).and(imageEntity.deletedAt.isNull()))
                        .orderBy(imageEntity.createdAt.desc())
                        .fetchFirst())
                .map(imageMapper::toDomain);
    }
}
