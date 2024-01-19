package tipitapi.drawmytodayimprovement.diary.mapper;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.emotion.entity.EmotionEntity;
import tipitapi.drawmytodayimprovement.user.entity.UserEntity;

@Component
@RequiredArgsConstructor
public class DiaryMapper {

	private final EntityManager entityManager;

	public Diary toDomain(DiaryEntity diaryEntity) {
		return Diary.builder()
			.diaryId(diaryEntity.getId())
			.createdAt(diaryEntity.getCreatedAt())
			.updatedAt(diaryEntity.getUpdatedAt())
			.emotionId(diaryEntity.getEmotion().getId())
			.userId(diaryEntity.getEmotion().getId())
			.diaryDate(diaryEntity.getDiaryDate())
			.notes(diaryEntity.getNotes())
			.isAi(diaryEntity.isAi())
			.title(diaryEntity.getTitle())
			.weather(diaryEntity.getWeather())
			.deletedAt(diaryEntity.getDeletedAt())
			.isTest(diaryEntity.isTest())
			.build();
	}

	// TODO: 노트 암호화 이슈
	public DiaryEntity toEntity(Diary diary) {
		return DiaryEntity.builder()
			.id(diary.getDiaryId())
			.user(entityManager.getReference(UserEntity.class, diary.getUserId()))
			.emotion(entityManager.getReference(EmotionEntity.class, diary.getEmotionId()))
			.diaryDate(diary.getDiaryDate())
			.notes(diary.getRowNotes())
			.isAi(diary.isAi())
			.title(diary.getTitle())
			.weather(diary.getWeather())
			.deletedAt(diary.getDeletedAt())
			.isTest(diary.isTest())
			.build();
	}
}
