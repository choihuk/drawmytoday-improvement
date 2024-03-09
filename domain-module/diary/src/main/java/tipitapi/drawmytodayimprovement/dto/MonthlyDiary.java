package tipitapi.drawmytodayimprovement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tipitapi.drawmytodayimprovement.domain.ImageUrlConvertable;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MonthlyDiary implements ImageUrlConvertable {

    private Long diaryId;
    private String imageUrl;
    private LocalDateTime diaryDate;

    public static MonthlyDiary of(Long diaryId, String imageUrl, LocalDateTime diaryDate) {
        return new MonthlyDiary(diaryId, imageUrl, diaryDate);
    }

    @Override
    public void convertImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
