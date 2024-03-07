package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.repository.PromptRepository;

@Service
@RequiredArgsConstructor
public class PromptService {

    private final PromptRepository promptRepository;

    public Prompt getOrElseEmptyPrompt(Long imageId) {
        return promptRepository.findByImageId(imageId)
                .orElse(Prompt.createEmptyPrompt());
    }

    public Prompt save(Prompt prompt) {
        return promptRepository.save(prompt);
    }
}
