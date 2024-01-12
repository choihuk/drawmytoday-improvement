package tipitapi.drawmytodayimprovement.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.exception.DiaryNotFoundException;
import tipitapi.drawmytodayimprovement.exception.NotOwnerOfDiaryException;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;

@Service
@RequiredArgsConstructor
public class ValidateDiaryService {

	private final DiaryRepository diaryRepository;

	public Diary validateDiaryById(Long diaryId, Long userId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow(DiaryNotFoundException::new);
		if (!Objects.equals(diary.getUserId(), userId)) {
			throw new NotOwnerOfDiaryException();
		}
		return diary;
	}
}
