package tipitapi.drawmytodayimprovement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tipitapi.drawmytodayimprovement.domain.ImageUrlConvertable;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ImageForMonitoring implements ImageUrlConvertable {

    private Long diaryId;
    private String prompt;
    private LocalDateTime createdAt;
    private LocalDateTime imageCreatedAt;
    private String imageUrl;
    private String review;
    private boolean isTest;

    @Override
    public void convertImageUrl(String customDomainUrl) {
        this.imageUrl = customDomainUrl;
    }
}
