package tipitapi.drawmytodayimprovement.prompt.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.prompt.mapper.PromptMapper;
import tipitapi.drawmytodayimprovement.repository.PromptRepository;

import java.util.Optional;

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

    @Override
    public Optional<Prompt> findByImageId(Long diaryId) {
        return promptJpaRepository.findAllSuccessPromptByImageId(diaryId).stream()
                .findFirst()
                .map(promptMapper::toDomain);
    }
}
