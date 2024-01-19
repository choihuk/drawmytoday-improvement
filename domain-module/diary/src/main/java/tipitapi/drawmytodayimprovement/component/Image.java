package tipitapi.drawmytodayimprovement.component;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Image {

	private Long imageId;
	private Long diaryId;
	private LocalDateTime createdAt;
	private String imageUrl;
	private boolean isSelected;
	private String review;
	private LocalDateTime deletedAt;

	public static Image create(Long diaryId, String imagePath, boolean isSelected) {
		return Image.builder()
			.diaryId(diaryId)
			.imageUrl(imagePath)
			.isSelected(isSelected)
			.build();
	}

	public void convertImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
