package tipitapi.drawmytodayimprovement.testdata;

import tipitapi.drawmytodayimprovement.domain.Image;

import java.time.LocalDateTime;

public class TestImage {

    public static Image createTestImage(Long imageId, Long diaryId) {
        return Image.builder()
                .imageId(imageId)
                .diaryId(diaryId)
                .createdAt(LocalDateTime.now())
                .imageUrl("imageUrl")
                .isSelected(true)
                .review("review")
                .build();
    }
}
