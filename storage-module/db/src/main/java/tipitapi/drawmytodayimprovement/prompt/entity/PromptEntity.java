package tipitapi.drawmytodayimprovement.prompt.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "prompt")
public class PromptEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prompt_id")
    private Long id;

    @NotNull
    @Column(length = 1100)
    private String promptText;

    @NotNull
    private boolean isSuccess;

    @Embedded
    private PromptGeneratorResultVO promptGeneratorResult;

    @Builder
    private PromptEntity(Long id, String promptText, boolean isSuccess,
                         PromptGeneratorResultVO promptGeneratorResult) {
        this.id = id;
        this.promptText = promptText;
        this.isSuccess = isSuccess;
        this.promptGeneratorResult = promptGeneratorResult;
    }
}
