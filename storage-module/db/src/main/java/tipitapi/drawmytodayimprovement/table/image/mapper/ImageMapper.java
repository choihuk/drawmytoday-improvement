package tipitapi.drawmytodayimprovement.table.image.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.table.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.table.image.entity.ImageEntity;
import tipitapi.drawmytodayimprovement.table.prompt.entity.PromptEntity;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    private final EntityManager entityManager;

    public Image toDomain(ImageEntity imageEntity) {
        return Image.builder()
                .imageId(imageEntity.getId())
                .createdAt(imageEntity.getCreatedAt())
                .diaryId(imageEntity.getDiary().getId())
                .promptId(imageEntity.getPrompt().getId())
                .imageUrl(imageEntity.getImageUrl())
                .isSelected(imageEntity.isSelected())
                .review(imageEntity.getReview())
                .deletedAt(imageEntity.getDeletedAt())
                .build();
    }

    public ImageEntity toEntity(Image image) {
        return ImageEntity.builder()
                .id(image.getImageId())
                .diary(entityManager.getReference(DiaryEntity.class, image.getDiaryId()))
                .prompt(entityManager.getReference(PromptEntity.class, image.getPromptId()))
                .imageUrl(image.getImageUrl())
                .isSelected(image.isSelected())
                .review(image.getReview())
                .deletedAt(image.getDeletedAt())
                .build();
    }
}
