package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.repository.EmotionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;

    public List<Emotion> getActiveEmotions() {
        return emotionRepository.findAllActiveEmotions();
    }
}
