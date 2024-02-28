package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Prompt;
import tipitapi.drawmytodayimprovement.repository.PromptRepository;

@Service
@RequiredArgsConstructor
public class PromptService {

    private final PromptRepository promptRepository;

    public Prompt createSuccessPrompt(Diary diary, String prompt) {
        return promptRepository.save(Prompt.createSuccessPrompt(diary.getDiaryId(), prompt));
    }

    public Prompt createFailedPrompt(String prompt) {
        return promptRepository.save(Prompt.createFailedPrompt(prompt));
    }

    public Prompt getOrElseEmptyPrompt(Long diaryId) {
        return promptRepository.findByDiaryId(diaryId)
                .orElse(Prompt.createEmptyPrompt());
    }
}
