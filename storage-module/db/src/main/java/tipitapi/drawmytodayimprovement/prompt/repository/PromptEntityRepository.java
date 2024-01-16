package tipitapi.drawmytodayimprovement.prompt.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Prompt;
import tipitapi.drawmytodayimprovement.prompt.mapper.PromptMapper;
import tipitapi.drawmytodayimprovement.repository.PromptRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class PromptEntityRepository implements PromptRepository {

	private final PromptJpaRepository promptJpaRepository;
	private final PromptMapper promptMapper;

	@Override
	@Transactional
	public Prompt save(Prompt prompt) {
		return promptMapper.toDomain(promptJpaRepository.save(promptMapper.toEntity(prompt)));
	}
}
