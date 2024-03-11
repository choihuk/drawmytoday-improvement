package tipitapi.drawmytodayimprovement.table.diary.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tipitapi.drawmytodayimprovement.common.BaseEntityWithUpdate;
import tipitapi.drawmytodayimprovement.table.emotion.entity.EmotionEntity;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "diary")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
@SQLDelete(sql = "UPDATE diary SET deleted_at = current_timestamp WHERE diary_id = ?")
public class DiaryEntity extends BaseEntityWithUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id", nullable = false)
    private EmotionEntity emotion;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime diaryDate;

    @Column(length = 8013)
    private String notes;

    @NotNull
    @Column(nullable = false)
    private boolean isAi;

    @Column(length = 42)
    private String title;

    @Column(length = 32)
    private String weather;

    private LocalDateTime deletedAt;

    @NotNull
    @Column(nullable = false)
    private boolean isTest;

    @Builder
    private DiaryEntity(Long id, UserEntity user, EmotionEntity emotion, LocalDateTime diaryDate,
                        String notes, boolean isAi, String title, String weather, LocalDateTime deletedAt, boolean isTest) {
        this.id = id;
        this.user = user;
        this.emotion = emotion;
        this.diaryDate = diaryDate;
        this.notes = notes;
        this.isAi = isAi;
        this.title = title;
        this.weather = weather;
        this.deletedAt = deletedAt;
        this.isTest = isTest;
    }
}

