package tipitapi.drawmytodayimprovement.prompt.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.domain.PromptGeneratorResult;
import tipitapi.drawmytodayimprovement.prompt.entity.PromptEntity;
import tipitapi.drawmytodayimprovement.prompt.entity.PromptGeneratorResultVO;

@Component
@RequiredArgsConstructor
public class PromptMapper {

    public Prompt toDomain(PromptEntity promptEntity) {
        return Prompt.builder()
                .promptId(promptEntity.getId())
                .createdAt(promptEntity.getCreatedAt())
                .promptText(promptEntity.getPromptText())
                .promptGeneratorResult(toDomain(promptEntity.getPromptGeneratorResult()))
                .isSuccess(promptEntity.isSuccess())
                .build();
    }

    public PromptEntity toEntity(Prompt prompt) {
        return PromptEntity.builder()
                .id(prompt.getPromptId())
                .promptText(prompt.getPromptText())
                .promptGeneratorResult(toEntity(prompt.getPromptGeneratorResult()))
                .isSuccess(prompt.isSuccess())
                .build();
    }

    public PromptGeneratorResult toDomain(PromptGeneratorResultVO promptGeneratorResultVO) {
        return PromptGeneratorResult.builder()
                .promptGeneratorType(promptGeneratorResultVO.getPromptGeneratorType())
                .promptGeneratorContent(promptGeneratorResultVO.getPromptGeneratorContent())
                .build();
    }

    public PromptGeneratorResultVO toEntity(PromptGeneratorResult promptGeneratorResult) {
        return PromptGeneratorResultVO.builder()
                .promptGeneratorType(promptGeneratorResult.getPromptGeneratorType())
                .promptGeneratorContent(promptGeneratorResult.getPromptGeneratorContent())
                .build();
    }
}
