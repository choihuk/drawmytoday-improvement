package tipitapi.drawmytodayimprovement.prompt.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import tipitapi.drawmytodayimprovement.domain.PromptGeneratorType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromptGeneratorResultVO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private PromptGeneratorType promptGeneratorType;
    @Type(type = "text")
    private String promptGeneratorContent;

    @Builder
    private PromptGeneratorResultVO(PromptGeneratorType promptGeneratorType,
                                    String promptGeneratorContent) {
        this.promptGeneratorType = promptGeneratorType;
        this.promptGeneratorContent = promptGeneratorContent;
    }
}
