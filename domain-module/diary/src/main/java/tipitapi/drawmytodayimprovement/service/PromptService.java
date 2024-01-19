package tipitapi.drawmytodayimprovement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Prompt;
import tipitapi.drawmytodayimprovement.repository.PromptRepository;

@Service
@RequiredArgsConstructor
public class PromptService {

	private final PromptRepository promptRepository;

	@Transactional
	public Prompt createSuccessPrompt(Diary diary, String prompt) {
		return promptRepository.save(Prompt.createSuccessPrompt(diary.getDiaryId(), prompt));
	}

	@Transactional
	public Prompt createFailedPrompt(String prompt) {
		return promptRepository.save(Prompt.createFailedPrompt(prompt));
	}

	public Optional<Prompt> get(Long diaryId) {
		return promptRepository.findByDiaryId(diaryId);
	}
}
