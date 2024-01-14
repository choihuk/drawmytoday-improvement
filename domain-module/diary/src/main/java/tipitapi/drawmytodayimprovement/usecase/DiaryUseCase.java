package tipitapi.drawmytodayimprovement.usecase;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.service.ValidateDiaryService;

@Component
@RequiredArgsConstructor
public class DiaryUseCase {

	private final ValidateDiaryService validateDiaryService;

	@Transactional(readOnly = true)
	public Diary getDiary(Long userId, Long diaryId) {
		return validateDiaryService.validateDiaryById(diaryId, userId);
	}
}
