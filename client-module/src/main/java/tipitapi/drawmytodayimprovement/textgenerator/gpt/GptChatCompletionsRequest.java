package tipitapi.drawmytodayimprovement.textgenerator.gpt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * https://platform.openai.com/docs/guides/text-generation/chat-completions-api
 */

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class GptChatCompletionsRequest {

    private final String model;
    private final List<MessageResponse> messages;

    private GptChatCompletionsRequest(String gptChatCompletionsPrompt) {
        this.model = "gpt-3.5-turbo";
        List<MessageResponse> messages = new ArrayList<>();
        messages.add(new MessageResponse(ChatCompletionsRole.system, gptChatCompletionsPrompt));
        this.messages = messages;
    }

    public static GptChatCompletionsRequest createFirstMessage(String gptChatCompletionsPrompt,
                                                               String diaryNote) {
        GptChatCompletionsRequest request = new GptChatCompletionsRequest(gptChatCompletionsPrompt);
        request.addUserMessage(diaryNote);
        return request;
    }

    private void addUserMessage(String userMessage) {
        this.messages.add(new MessageResponse(ChatCompletionsRole.user, userMessage));
    }
}
