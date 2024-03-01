package tipitapi.drawmytodayimprovement.diary.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.diary.mapper.DiaryMapper;
import tipitapi.drawmytodayimprovement.diary.vo.MonthlyDiaryVo;
import tipitapi.drawmytodayimprovement.diary.vo.QMonthlyDiaryVo;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.MonthlyDiary;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tipitapi.drawmytodayimprovement.diary.entity.QDiaryEntity.diaryEntity;
import static tipitapi.drawmytodayimprovement.image.entity.QImageEntity.imageEntity;

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
        return queryFactory.select(
                        new QMonthlyDiaryVo(diaryEntity.id, imageEntity.imageUrl.max(), diaryEntity.diaryDate))
                .from(diaryEntity)
                .leftJoin(imageEntity)
                .on(diaryEntity.id.eq(imageEntity.diary.id)
                        .and(imageEntity.isSelected.eq(true)))
                .where(diaryEntity.diaryDate.between(startMonth, endMonth)
                        .and(diaryEntity.user.id.eq(userId)))
                .orderBy(diaryEntity.diaryDate.asc())
                .groupBy(diaryEntity.id)
                .fetch()
                .stream()
                .map(MonthlyDiaryVo::toDomain)
                .collect(Collectors.toList());
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
}
