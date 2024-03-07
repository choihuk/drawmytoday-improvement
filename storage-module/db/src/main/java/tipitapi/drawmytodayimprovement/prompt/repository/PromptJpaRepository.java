package tipitapi.drawmytodayimprovement.prompt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tipitapi.drawmytodayimprovement.prompt.entity.PromptEntity;

import java.util.List;

public interface PromptJpaRepository extends JpaRepository<PromptEntity, Long> {
    @Query("SELECT p FROM ImageEntity i JOIN i.prompt p WHERE i.id = :imageId AND p.isSuccess = true")
    List<PromptEntity> findAllSuccessPromptByImageId(Long imageId);
}
