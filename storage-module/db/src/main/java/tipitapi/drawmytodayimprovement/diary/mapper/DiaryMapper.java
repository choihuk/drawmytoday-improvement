package tipitapi.drawmytodayimprovement.diary.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.emotion.entity.EmotionEntity;
import tipitapi.drawmytodayimprovement.emotion.mapper.EmotionMapper;

@Component
@RequiredArgsConstructor
public class DiaryMapper {

	private final EmotionMapper emotionMapper;

	public Diary mapToDiary(DiaryEntity diaryEntity, EmotionEntity emotionEntity) {
		return Diary.builder()
			.diaryId(diaryEntity.getId())
			.createdAt(diaryEntity.getCreatedAt())
			.emotion(emotionMapper.toDomain(emotionEntity))
			.userId(diaryEntity.getUserEntity().getId())
			.diaryDate(diaryEntity.getDiaryDate())
			.notes(diaryEntity.getNotes())
			.isAi(diaryEntity.isAi())
			.title(diaryEntity.getTitle())
			.weather(diaryEntity.getWeather())
			.deletedAt(diaryEntity.getDeletedAt())
			.isTest(diaryEntity.isTest())
			.build();
	}
}
