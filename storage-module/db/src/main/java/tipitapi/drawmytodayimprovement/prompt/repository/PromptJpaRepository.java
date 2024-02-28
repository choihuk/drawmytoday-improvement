package tipitapi.drawmytodayimprovement.prompt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.prompt.entity.PromptEntity;

public interface PromptJpaRepository extends JpaRepository<PromptEntity, Long> {
	Optional<PromptEntity> findByDiaryId(Long diaryId);
}
