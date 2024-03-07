package tipitapi.drawmytodayimprovement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.domain.PromptGeneratorResult;
import tipitapi.drawmytodayimprovement.textgenerator.TextGenerator;
import tipitapi.drawmytodayimprovement.textgenerator.TextGeneratorContent;
import tipitapi.drawmytodayimprovement.textgenerator.TextGeneratorException;
import tipitapi.drawmytodayimprovement.utils.SystemEnv;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptTextCreator {

    private final String defaultStyle = SystemEnv.get("KARLO_DEFAULT_STYLE");
    private final TextGenerator gpt3TextGenerator;
    private final ObjectMapper objectMapper;

    public Prompt createPromptUsingGpt(Emotion emotion, String diaryNote) {
        String promptText;
        PromptGeneratorResult result = null;
        if (!StringUtils.hasText(diaryNote)) {
            promptText = "portrait";
        } else {
            try {
                List<? extends TextGeneratorContent> gptResult = gpt3TextGenerator.generatePrompt(diaryNote);
                String content = objectMapper.writeValueAsString(gptResult);
                promptText = gptResult.get(gptResult.size() - 1).getContent();
                result = PromptGeneratorResult.createGpt3Result(content);
            } catch (TextGeneratorException e) {
                promptText = diaryNote;
                result = PromptGeneratorResult.createNoUse();
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("GPT 결과를 JSON으로 변환하는데 실패했습니다.", e);
            }
        }

        String finalPromptText = promptTextBuilder(
                emotion.getEmotionPrompt(),
                emotion.getColorPrompt(),
                defaultStyle,
                promptText);
        return Prompt.create(result, finalPromptText);
    }

    private String promptTextBuilder(String... prompts) {
        StringBuilder sb = new StringBuilder();
        for (String prompt : prompts) {
            if (StringUtils.hasText(prompt)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(prompt);
            }
        }
        if (sb.length() == 0) {
            return "";
        }
        if (sb.length() > 1000) {
            return sb.substring(0, 1000);
        }
        return sb.toString();
    }
}
