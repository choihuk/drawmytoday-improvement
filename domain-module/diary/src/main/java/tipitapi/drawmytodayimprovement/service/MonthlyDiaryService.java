package tipitapi.drawmytodayimprovement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.MonthlyDiary;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;
import tipitapi.drawmytodayimprovement.utils.DateUtils;

@Service
@RequiredArgsConstructor
public class MonthlyDiaryService {

	private final DiaryRepository diaryRepository;

	public List<MonthlyDiary> getMonthlyDiaries(Long userId, int year, int month) {
		LocalDateTime startMonth = DateUtils.getStartDate(year, month);
		LocalDateTime endMonth = DateUtils.getEndDate(year, month);
		return diaryRepository.findMonthlyDiaries(userId, startMonth, endMonth);
	}
}
