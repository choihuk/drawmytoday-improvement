package tipitapi.drawmytodayimprovement.textgenerator.gpt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.textgenerator.TextGeneratorContent;

@Getter
@NoArgsConstructor(force = true)
public class MessageResponse implements TextGeneratorContent {

    private final ChatCompletionsRole role;
    private final String content;

    public MessageResponse(ChatCompletionsRole role, String content) {
        this.role = role;
        this.content = content;
    }
}
