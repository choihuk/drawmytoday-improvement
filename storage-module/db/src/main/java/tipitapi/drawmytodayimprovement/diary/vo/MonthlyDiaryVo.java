package tipitapi.drawmytodayimprovement.diary.vo;

import com.querydsl.core.annotations.QueryProjection;
import tipitapi.drawmytodayimprovement.domain.MonthlyDiary;

import java.time.LocalDateTime;

public record MonthlyDiaryVo(Long diaryId, String imageUrl, LocalDateTime diaryDate) {

    @QueryProjection
    public MonthlyDiaryVo {
    }

    public MonthlyDiary toDomain() {
        return MonthlyDiary.of(diaryId, imageUrl, diaryDate);
    }
}
