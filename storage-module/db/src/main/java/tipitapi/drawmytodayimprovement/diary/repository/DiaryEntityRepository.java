package tipitapi.drawmytodayimprovement.diary.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.diary.mapper.DiaryMapper;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class DiaryEntityRepository implements DiaryRepository {

	private final DiaryJpaRepository diaryJpaRepository;
	private final DiaryMapper diaryMapper;

	@Override
	public Optional<Diary> findById(Long diaryId) {
		return diaryJpaRepository.findById(diaryId)
			.map(diaryEntity -> diaryMapper.mapToDiary(
				diaryEntity, diaryEntity.getEmotionEntity()
			));
	}
}
