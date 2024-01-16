package tipitapi.drawmytodayimprovement.emotion.mapper;

import org.springframework.stereotype.Component;

import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.emotion.entity.EmotionEntity;

@Component
public class EmotionMapper {

	public Emotion toDomain(EmotionEntity emotionEntity) {
		return Emotion.builder()
			.emotionId(emotionEntity.getId())
			.createdAt(emotionEntity.getCreatedAt())
			.name(emotionEntity.getName())
			.color(emotionEntity.getColor())
			.isActive(emotionEntity.isActive())
			.emotionPrompt(emotionEntity.getEmotionPrompt())
			.colorPrompt(emotionEntity.getColorPrompt())
			.build();
	}

	public EmotionEntity toEntity(Emotion emotion) {
		return EmotionEntity.builder()
			.id(emotion.getEmotionId())
			.name(emotion.getName())
			.color(emotion.getColor())
			.isActive(emotion.isActive())
			.emotionPrompt(emotion.getEmotionPrompt())
			.colorPrompt(emotion.getColorPrompt())
			.build();
	}
}
