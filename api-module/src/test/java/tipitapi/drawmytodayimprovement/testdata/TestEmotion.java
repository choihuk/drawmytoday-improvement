package tipitapi.drawmytodayimprovement.testdata;

import java.time.LocalDateTime;

import tipitapi.drawmytodayimprovement.component.Emotion;

public class TestEmotion {

	public static Emotion createTestEmotion(Long emotionId) {
		return Emotion.builder()
			.emotionId(emotionId)
			.createdAt(LocalDateTime.now())
			.name("name")
			.emotionPrompt("emotionPrompt")
			.color("color")
			.colorPrompt("colorPrompt")
			.isActive(true)
			.build();
	}
}
