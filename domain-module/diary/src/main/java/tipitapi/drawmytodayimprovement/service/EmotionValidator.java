package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.exception.EmotionNotFoundException;
import tipitapi.drawmytodayimprovement.repository.EmotionRepository;

@Service
@RequiredArgsConstructor
public class EmotionValidator {

    private final EmotionRepository emotionRepository;

    public Emotion validateByEmotionId(Long emotionId) {
        return emotionRepository.findById(emotionId).orElseThrow(EmotionNotFoundException::new);
    }

    public Emotion validateByDiaryId(Long diaryId) {
        return emotionRepository.findByDiaryId(diaryId).orElseThrow(EmotionNotFoundException::new);
    }
}
