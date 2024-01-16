package tipitapi.drawmytodayimprovement.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Diary;

@Repository
public interface DiaryRepository {

	Optional<Diary> findById(Long diaryId);

	boolean existByUserIdAndDiaryDate(Long userId, LocalDate diaryDate);

	Diary save(Diary diary);
}
