package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Prompt;

@Repository
public interface PromptRepository {
	Prompt save(Prompt successPrompt);
}
