package tipitapi.drawmytodayimprovement.usecase;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.service.ValidateDiaryService;

@Service
@RequiredArgsConstructor
public class DiaryUseCase {

	private final ValidateDiaryService validateDiaryService;

	public Diary getDiary(Long userId, Long diaryId) {
		return validateDiaryService.validateDiaryById(diaryId, userId);
	}
}
