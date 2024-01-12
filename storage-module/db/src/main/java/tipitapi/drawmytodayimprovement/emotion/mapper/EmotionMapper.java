package tipitapi.drawmytodayimprovement.emotion.mapper;

import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.emotion.entity.EmotionEntity;

public class EmotionMapper {

	public static Emotion mapToEmotion(EmotionEntity emotionEntity) {
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
