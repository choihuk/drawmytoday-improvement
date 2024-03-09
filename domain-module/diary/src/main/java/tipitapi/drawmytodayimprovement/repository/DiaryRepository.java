package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.dto.MonthlyDiary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository {

    Optional<Diary> findById(Long diaryId);

    boolean existByUserIdAndDiaryDate(Long userId, LocalDate diaryDate);

    Diary save(Diary diary);

    List<MonthlyDiary> findMonthlyDiaries(Long userId, LocalDateTime startMonth, LocalDateTime endMonth);

    Optional<Diary> getDiaryExistsByDiaryDate(Long userId, LocalDate date);

    Optional<Diary> findFirstByUserIdOrderByCreatedAtDesc(Long userId);

    void delete(Diary diary);
}
