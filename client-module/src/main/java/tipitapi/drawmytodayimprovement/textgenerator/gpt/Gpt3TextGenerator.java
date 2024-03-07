package tipitapi.drawmytodayimprovement.textgenerator.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.textgenerator.TextGenerator;
import tipitapi.drawmytodayimprovement.textgenerator.gpt.exception.GptRequestFailException;
import tipitapi.drawmytodayimprovement.utils.SystemEnv;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@Service
class Gpt3TextGenerator implements TextGenerator {

    private final String chatCompletionsUrl;
    private final String gptChatCompletionsPrompt;
    private final String openaiApiKey;
    private final ObjectMapper objectMapper;

    public Gpt3TextGenerator(@Value("${openai.gpt.chat_completions_prompt}") String gptChatCompletionsPrompt,
                             ObjectMapper objectMapper) {
        this.chatCompletionsUrl = "https://api.openai.com/v1/chat/completions";
        this.gptChatCompletionsPrompt = gptChatCompletionsPrompt;
        this.openaiApiKey = SystemEnv.get("OPENAI_API_KEY");
        this.objectMapper = objectMapper;
    }

    @Override
    public List<MessageResponse> generatePrompt(String diaryNote) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            GptChatCompletionsRequest httpRequestBody = GptChatCompletionsRequest.createFirstMessage(
                    gptChatCompletionsPrompt, diaryNote);
            HttpRequest httpRequest = createChatCompletionsRequest(httpRequestBody);

            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != HttpURLConnection.HTTP_OK) {
                log.warn("GPT chat completions 요청에 실패했습니다. status code: {}", httpResponse.statusCode());
                throw new GptRequestFailException();
            }

            GptChatCompletionsResponse gptChatCompletionsResponse = objectMapper.readValue(
                    httpResponse.body(), GptChatCompletionsResponse.class);
            validIsSuccessfulRequest(gptChatCompletionsResponse);
            List<MessageResponse> messages = httpRequestBody.getMessages();
            messages.add(gptChatCompletionsResponse.getChoices()[0].getMessage());
            return messages;
        } catch (InterruptedException | IOException e) {
            log.warn("GPT chat completions 요청에 실패했습니다. message: {}", e.getMessage());
            throw new GptRequestFailException(e);
        }
    }

    private HttpRequest createChatCompletionsRequest(GptChatCompletionsRequest httpRequestBody)
            throws JsonProcessingException {
        return HttpRequest.newBuilder()
                .uri(URI.create(chatCompletionsUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + openaiApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(
                        objectMapper.writeValueAsString(httpRequestBody)
                )).build();
    }

    private static void validIsSuccessfulRequest(GptChatCompletionsResponse response) {
        if (response == null
                || response.getChoices() == null
                || response.getChoices().length == 0
                || response.getChoices()[0].getMessage() == null
                || response.getChoices()[0].getMessage().getContent() == null
                || response.getChoices()[0].getMessage().getContent().isBlank()) {
            log.warn("GPT chat completions 응답 파싱에 실패했습니다. response: {}", response);
            throw new GptRequestFailException();
        }
    }
}
