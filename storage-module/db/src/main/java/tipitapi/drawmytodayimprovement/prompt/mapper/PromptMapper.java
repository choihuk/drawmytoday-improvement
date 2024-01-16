package tipitapi.drawmytodayimprovement.prompt.mapper;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Prompt;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.prompt.entity.PromptEntity;

@Component
@RequiredArgsConstructor
public class PromptMapper {

	private final EntityManager entityManager;

	public Prompt toDomain(PromptEntity promptEntity) {
		return Prompt.builder()
			.promptId(promptEntity.getId())
			.createdAt(promptEntity.getCreatedAt())
			.diaryId(getDiaryId(promptEntity))
			.promptText(promptEntity.getPromptText())
			.isSuccess(promptEntity.isSuccess())
			.build();
	}

	public PromptEntity toEntity(Prompt prompt) {
		return PromptEntity.builder()
			.id(prompt.getPromptId())
			.diary(getDiaryEntity(prompt.getDiaryId()))
			.promptText(prompt.getPromptText())
			.isSuccess(prompt.isSuccess())
			.build();
	}

	private Long getDiaryId(PromptEntity promptEntity) {
		return promptEntity.getDiary() != null ? promptEntity.getDiary().getId() : null;
	}

	private DiaryEntity getDiaryEntity(Long diaryId) {
		return diaryId != null ? entityManager.getReference(DiaryEntity.class, diaryId) : null;
	}
}
