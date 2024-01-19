package tipitapi.drawmytodayimprovement.diary.repository;

import static tipitapi.drawmytodayimprovement.diary.entity.QDiaryEntity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.diary.mapper.DiaryMapper;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;

@Repository
@Transactional(readOnly = true)
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
		LocalDateTime start = diaryDate.atStartOfDay();
		LocalDateTime end = diaryDate.atTime(23, 59, 59);

		return queryFactory.selectOne()
			.from(diaryEntity)
			.where(diaryEntity.user.id.eq(userId)
				.and(diaryEntity.diaryDate.between(start, end)))
			.fetchFirst() != null;
	}

	@Override
	public Diary save(Diary diary) {
		return diaryMapper.toDomain(
			diaryJpaRepository.save(diaryMapper.toEntity(diary))
		);
	}
}
