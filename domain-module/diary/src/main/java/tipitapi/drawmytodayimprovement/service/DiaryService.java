package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.MonthlyDiary;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;
import tipitapi.drawmytodayimprovement.utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public List<MonthlyDiary> getMonthlyDiaries(Long userId, int year, int month) {
        LocalDateTime startMonth = DateUtils.getStartDate(year, month);
        LocalDateTime endMonth = DateUtils.getEndDate(year, month);
        return diaryRepository.findMonthlyDiaries(userId, startMonth, endMonth);
    }

    public Optional<Diary> getDiaryExistsByDiaryDate(Long userId, LocalDate diaryDate) {
        return diaryRepository.getDiaryExistsByDiaryDate(userId, diaryDate);
    }

    public Optional<Diary> getLastCreation(Long userId) {
        return diaryRepository.findFirstByUserIdOrderByCreatedAtDesc(userId);
    }

    public void deleteDiary(Diary diary) {
        diaryRepository.delete(diary);
    }
}
