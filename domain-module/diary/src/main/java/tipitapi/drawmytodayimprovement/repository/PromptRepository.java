package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Prompt;

import java.util.Optional;

@Repository
public interface PromptRepository {
    Prompt save(Prompt prompt);

    Optional<Prompt> findByImageId(Long diaryId);
}
