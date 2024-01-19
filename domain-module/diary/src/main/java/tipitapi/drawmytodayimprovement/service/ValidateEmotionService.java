package tipitapi.drawmytodayimprovement.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.exception.EmotionNotFoundException;
import tipitapi.drawmytodayimprovement.repository.EmotionRepository;

@Service
@RequiredArgsConstructor
public class ValidateEmotionService {

	private final EmotionRepository emotionRepository;

	public Emotion validateById(Long emotionId) {
		return emotionRepository.findById(emotionId).orElseThrow(EmotionNotFoundException::new);
	}

	public Emotion validateByDiaryId(Long diaryId) {
		return emotionRepository.findByDiaryId(diaryId).orElseThrow(EmotionNotFoundException::new);
	}
}
