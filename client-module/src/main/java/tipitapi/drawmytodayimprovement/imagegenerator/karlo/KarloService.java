package tipitapi.drawmytodayimprovement.imagegenerator.karlo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorService;
import tipitapi.drawmytodayimprovement.imagegenerator.karlo.exception.ImageInputStreamFailException;
import tipitapi.drawmytodayimprovement.imagegenerator.karlo.exception.KarloRequestFailException;
import tipitapi.drawmytodayimprovement.utils.SystemEnv;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
class KarloService implements ImageGeneratorService {

    private final String karloImageCreateUrl;
    private final String negativePrompt;
    private final String kakaoApiKey;
    private final ObjectMapper objectMapper;

    public KarloService(ObjectMapper objectMapper) {
        this.karloImageCreateUrl = SystemEnv.get("KARLO_API_URL");
        this.negativePrompt = SystemEnv.get("KARLO_NEGATIVE_PROMPT");
        this.kakaoApiKey = SystemEnv.get("KAKAO_API_KEY");
        this.objectMapper = objectMapper;
    }

    @Override
    public List<byte[]> generateImage(String prompt) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(karloImageCreateUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "KakaoAK " + kakaoApiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(
                                    objectMapper.writeValueAsString(
                                            CreateKarloImageRequest.createRequest(prompt, negativePrompt)
                                    )
                            )
                    ).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException("Failed to get karlo image. "
                        + "Response: " + response.statusCode() + ", " + response.body());
            }

            return objectMapper.readValue(response.body(), KarloUrlResponse.class)
                    .images()
                    .stream()
                    .map(karloImageUrlResponse -> {
                        try (InputStream inputStream = new URL(karloImageUrlResponse.imageUrl()).openStream()) {
                            return inputStream.readAllBytes();
                        } catch (IOException e) {
                            throw new ImageInputStreamFailException(e);
                        }
                    }).collect(Collectors.toList());
        } catch (InterruptedException | IOException e) {
            throw new KarloRequestFailException(e);
        }
    }
}
