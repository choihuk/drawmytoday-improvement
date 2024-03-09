package tipitapi.drawmytodayimprovement.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Image implements ImageUrlConvertable {

    private Long imageId;
    private Long diaryId;
    private Long promptId;
    private LocalDateTime createdAt;
    private String imageUrl;
    private boolean isSelected;
    private String review;
    private LocalDateTime deletedAt;

    @Builder
    private Image(Long imageId, Long diaryId, Long promptId, LocalDateTime createdAt,
                  String imageUrl, boolean isSelected, String review, LocalDateTime deletedAt) {
        this.imageId = imageId;
        this.diaryId = diaryId;
        this.promptId = promptId;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.isSelected = isSelected;
        this.review = review;
        this.deletedAt = deletedAt;
    }

    public static Image create(Long diaryId, Long promptId, String imagePath, boolean isSelected) {
        return Image.builder()
                .diaryId(diaryId)
                .promptId(promptId)
                .imageUrl(imagePath)
                .isSelected(isSelected)
                .build();
    }

    @Override
    public void convertImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void makeMainImage() {
        this.isSelected = true;
    }

    public void makeSubImage() {
        this.isSelected = false;
    }
}
