package tipitapi.drawmytodayimprovement.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.MonthlyDiary;

@Repository
public interface DiaryRepository {

	Optional<Diary> findById(Long diaryId);

	boolean existByUserIdAndDiaryDate(Long userId, LocalDate diaryDate);

	Diary save(Diary diary);

	List<MonthlyDiary> findMonthlyDiaries(Long userId, LocalDateTime startMonth, LocalDateTime endMonth);
}
