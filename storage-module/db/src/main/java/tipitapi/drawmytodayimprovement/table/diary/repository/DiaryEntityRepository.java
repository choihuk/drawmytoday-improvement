package tipitapi.drawmytodayimprovement.table.diary.repository;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.dto.MonthlyDiary;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;
import tipitapi.drawmytodayimprovement.table.diary.mapper.DiaryMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static tipitapi.drawmytodayimprovement.table.diary.entity.QDiaryEntity.diaryEntity;
import static tipitapi.drawmytodayimprovement.table.image.entity.QImageEntity.imageEntity;

@Repository
@Transactional
@RequiredArgsConstructor
class DiaryEntityRepository implements DiaryRepository {

    private final DiaryJpaRepository diaryJpaRepository;
    private final DiaryMapper diaryMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Diary> findById(Long diaryId) {
        return diaryJpaRepository.findById(diaryId)
                .map(diaryMapper::toDomain);
    }

    @Override
    public boolean existByUserIdAndDiaryDate(Long userId, LocalDate diaryDate) {
        LocalDateTime startDate = diaryDate.atStartOfDay();
        LocalDateTime endDate = diaryDate.atTime(23, 59, 59);

        return queryFactory.selectOne()
                .from(diaryEntity)
                .where(diaryEntity.user.id.eq(userId)
                        .and(diaryEntity.diaryDate.between(startDate, endDate)))
                .fetchFirst() != null;
    }

    @Override
    public Diary save(Diary diary) {
        return diaryMapper.toDomain(
                diaryJpaRepository.save(diaryMapper.toEntity(diary))
        );
    }

    @Override
    public List<MonthlyDiary> findMonthlyDiaries(Long userId, LocalDateTime startMonth, LocalDateTime endMonth) {
        ConstructorExpression<MonthlyDiary> constructor = Projections.constructor(MonthlyDiary.class,
                diaryEntity.id, imageEntity.imageUrl.max(), diaryEntity.diaryDate);
        return queryFactory.select(constructor)
                .from(diaryEntity)
                .leftJoin(imageEntity)
                .on(diaryEntity.id.eq(imageEntity.diary.id)
                        .and(imageEntity.isSelected.eq(true)))
                .where(diaryEntity.diaryDate.between(startMonth, endMonth)
                        .and(diaryEntity.user.id.eq(userId)))
                .orderBy(diaryEntity.diaryDate.asc())
                .groupBy(diaryEntity.id)
                .fetch();
    }

    @Override
    public Optional<Diary> getDiaryExistsByDiaryDate(Long userId, LocalDate diaryDate) {
        LocalDateTime startDate = diaryDate.atStartOfDay();
        LocalDateTime endDate = diaryDate.atTime(23, 59, 59);
        return Optional.ofNullable(
                queryFactory.selectFrom(diaryEntity)
                        .where(diaryEntity.diaryDate.between(startDate, endDate)
                                .and(diaryEntity.user.id.eq(userId)))
                        .fetchFirst()
        ).map(diaryMapper::toDomain);
    }

    @Override
    public Optional<Diary> findFirstByUserIdOrderByCreatedAtDesc(Long userId) {
        return diaryJpaRepository.findFirstByUserIdOrderByCreatedAtDesc(userId)
                .map(diaryMapper::toDomain);
    }

    @Override
    public void delete(Diary diary) {
        diaryJpaRepository.delete(diaryMapper.toEntity(diary));
    }
}
