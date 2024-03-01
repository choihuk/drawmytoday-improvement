package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.utils.SystemEnv;

@Service
public class PromptTextCreator {

    private final String defaultStyle;

    public PromptTextCreator() {
        this.defaultStyle = SystemEnv.get("KARLO_DEFAULT_STYLE");
    }

    public String createPromptText(Emotion emotion, String keyword) {
        if (!StringUtils.hasText(keyword)) {
            keyword = "portrait";
        }
        return promptTextBuilder(
                emotion.getEmotionPrompt(),
                emotion.getColorPrompt(),
                defaultStyle,
                keyword);
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
