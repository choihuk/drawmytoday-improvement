package tipitapi.drawmytodayimprovement.table.emotion.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emotion")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmotionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 30)
    private String name;

    @NotNull
    @Column(nullable = false, length = 30)
    private String color;

    @NotNull
    @Column(nullable = false)
    private boolean isActive;

    @NotNull
    @Column(nullable = false, length = 30)
    private String emotionPrompt;

    @NotNull
    @Column(nullable = false, length = 200)
    private String colorPrompt;

    @Builder
    private EmotionEntity(Long id, String name, String color, boolean isActive, String emotionPrompt,
                          String colorPrompt) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.isActive = isActive;
        this.emotionPrompt = emotionPrompt;
        this.colorPrompt = colorPrompt;
    }

}
