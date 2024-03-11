package tipitapi.drawmytodayimprovement.table.diary.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.table.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.table.emotion.entity.EmotionEntity;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.EntityManager;

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
