package tipitapi.drawmytodayimprovement.testdata;

import tipitapi.drawmytodayimprovement.domain.Emotion;

import java.time.LocalDateTime;

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
