package tipitapi.drawmytodayimprovement.table.image.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.table.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.table.prompt.entity.PromptEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@SQLDelete(sql = "UPDATE image SET deleted_at = current_timestamp WHERE image_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "image")
public class ImageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", nullable = false)
    private DiaryEntity diary;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prompt_id", nullable = false)
    private PromptEntity prompt;

    @NotNull
    @Column(nullable = false)
    private String imageUrl;

    @NotNull
    private boolean isSelected;

    private String review;

    private LocalDateTime deletedAt;

    @Builder
    private ImageEntity(Long id, DiaryEntity diary, PromptEntity prompt, String imageUrl,
                        boolean isSelected, String review, LocalDateTime deletedAt) {
        this.id = id;
        this.diary = diary;
        this.prompt = prompt;
        this.imageUrl = imageUrl;
        this.isSelected = isSelected;
        this.review = review;
        this.deletedAt = deletedAt;
    }
}

