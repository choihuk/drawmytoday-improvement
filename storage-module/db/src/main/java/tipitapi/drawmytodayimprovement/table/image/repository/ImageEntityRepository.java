package tipitapi.drawmytodayimprovement.table.image.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.dto.ImageForMonitoring;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;
import tipitapi.drawmytodayimprovement.table.emotion.entity.EmotionEntity;
import tipitapi.drawmytodayimprovement.table.image.mapper.ImageMapper;
import tipitapi.drawmytodayimprovement.util.PageableUtils;

import java.util.List;
import java.util.Optional;

import static tipitapi.drawmytodayimprovement.table.diary.entity.QDiaryEntity.diaryEntity;
import static tipitapi.drawmytodayimprovement.table.emotion.entity.QEmotionEntity.emotionEntity;
import static tipitapi.drawmytodayimprovement.table.image.entity.QImageEntity.imageEntity;
import static tipitapi.drawmytodayimprovement.table.prompt.entity.QPromptEntity.promptEntity;

@Repository
@Transactional
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

    @Override
    public List<Image> saveAll(List<Image> images) {
        return imageJpaRepository.saveAll(
                        images.stream()
                                .map(imageMapper::toEntity)
                                .toList()
                ).stream()
                .map(imageMapper::toDomain)
                .toList();
    }

    @Override
    public PageResponse<ImageForMonitoring> getImagesForMonitoring(PageableRequest pageableRequest,
                                                                   Long emotionId, boolean withTest) {
        Pageable pageable = PageableUtils.toPageable(pageableRequest);
        BooleanExpression withoutTest = withTest ? null : diaryEntity.isTest.eq(false);
        BooleanExpression withEmotion = null;
        if (emotionId != null) {
            EmotionEntity filterEmotion = queryFactory.selectFrom(emotionEntity)
                    .where(emotionEntity.id.eq(emotionId))
                    .fetchOne();
            if (filterEmotion != null) {
                withEmotion = diaryEntity.emotion.eq(filterEmotion);
            }
        }

        List<ImageForMonitoring> content = queryFactory.select(
                        Projections.constructor(ImageForMonitoring.class,
                                diaryEntity.id, promptEntity.promptText, diaryEntity.createdAt,
                                imageEntity.createdAt, imageEntity.imageUrl, imageEntity.review, diaryEntity.isTest)
                )
                .from(imageEntity)
                .leftJoin(diaryEntity).on(diaryEntity.id.eq(imageEntity.diary.id))
                .leftJoin(promptEntity).on(imageEntity.prompt.id.eq(promptEntity.id))
                .where(withoutTest, withEmotion)
                .orderBy(pageableRequest.isAscending() ? imageEntity.createdAt.asc() : imageEntity.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(diaryEntity.count()).from(diaryEntity)
                .where(withoutTest, withEmotion);

        Page<ImageForMonitoring> page = PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
        return PageableUtils.toPageResponse(page);
    }
}
