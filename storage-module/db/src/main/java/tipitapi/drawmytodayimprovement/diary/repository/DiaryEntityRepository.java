package tipitapi.drawmytodayimprovement.diary.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.diary.mapper.DiaryMapper;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;

@Repository
@RequiredArgsConstructor
class DiaryEntityRepository implements DiaryRepository {

	private final DiaryJpaRepository diaryJpaRepository;

	@Override
	public Optional<Diary> findById(Long diaryId) {
		Optional<DiaryEntity> diaryEntity1 = diaryJpaRepository.findById(diaryId);
		System.out.println("diaryEntity1 = " + diaryEntity1.isPresent());
		return diaryEntity1
			.map(diaryEntity -> DiaryMapper.mapToDiary(diaryEntity, diaryEntity.getEmotionEntity()));
	}
}
