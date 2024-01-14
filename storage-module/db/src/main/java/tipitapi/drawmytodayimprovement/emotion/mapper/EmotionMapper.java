package tipitapi.drawmytodayimprovement.emotion.mapper;

import org.springframework.stereotype.Component;

import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.emotion.entity.EmotionEntity;

@Component
public class EmotionMapper {

	public Emotion mapToEmotion(EmotionEntity emotionEntity) {
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
}
