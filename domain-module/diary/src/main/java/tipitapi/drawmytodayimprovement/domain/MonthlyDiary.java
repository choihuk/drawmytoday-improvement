package tipitapi.drawmytodayimprovement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MonthlyDiary {

    private Long diaryId;
    private String imageUrl;
    private LocalDateTime diaryDate;

    public static MonthlyDiary of(Long diaryId, String imageUrl, LocalDateTime diaryDate) {
        return new MonthlyDiary(diaryId, imageUrl, diaryDate);
    }

    public void convertImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
