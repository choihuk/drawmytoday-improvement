package tipitapi.drawmytodayimprovement.image.mapper;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;
import tipitapi.drawmytodayimprovement.image.entity.ImageEntity;

@Component
@RequiredArgsConstructor
public class ImageMapper {

	private final EntityManager entityManager;

	public Image toDomain(ImageEntity imageEntity) {
		return Image.builder()
			.imageId(imageEntity.getId())
			.createdAt(imageEntity.getCreatedAt())
			.diaryId(imageEntity.getDiary().getId())
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
			.imageUrl(image.getImageUrl())
			.isSelected(image.isSelected())
			.review(image.getReview())
			.deletedAt(image.getDeletedAt())
			.build();
	}
}
