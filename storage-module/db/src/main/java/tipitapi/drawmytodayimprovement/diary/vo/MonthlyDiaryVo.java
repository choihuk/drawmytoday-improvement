package tipitapi.drawmytodayimprovement.diary.vo;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import tipitapi.drawmytodayimprovement.component.MonthlyDiary;

public record MonthlyDiaryVo(Long diaryId, String imageUrl, LocalDateTime diaryDate) {

	@QueryProjection
	public MonthlyDiaryVo {
	}

	public MonthlyDiary toDomain() {
		return MonthlyDiary.of(diaryId, imageUrl, diaryDate);
	}
}
