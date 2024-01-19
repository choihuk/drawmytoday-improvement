package tipitapi.drawmytodayimprovement.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Prompt;

@Repository
public interface PromptRepository {
	Prompt save(Prompt successPrompt);

	Optional<Prompt> findByDiaryId(Long diaryId);
}
