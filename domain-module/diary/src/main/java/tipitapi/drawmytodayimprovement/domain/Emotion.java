package tipitapi.drawmytodayimprovement.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Emotion {

    private Long emotionId;
    private LocalDateTime createdAt;
    private String name;
    private String color;
    private boolean isActive;
    private String emotionPrompt;
    private String colorPrompt;

    @Builder
    private Emotion(Long emotionId, LocalDateTime createdAt, String name, String color,
                    boolean isActive, String emotionPrompt, String colorPrompt) {
        this.emotionId = emotionId;
        this.createdAt = createdAt;
        this.name = name;
        this.color = color;
        this.isActive = isActive;
        this.emotionPrompt = emotionPrompt;
        this.colorPrompt = colorPrompt;
    }
}
