package tipitapi.drawmytodayimprovement.prompt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.prompt.entity.PromptEntity;

interface PromptJpaRepository extends JpaRepository<PromptEntity, Long> {
}
