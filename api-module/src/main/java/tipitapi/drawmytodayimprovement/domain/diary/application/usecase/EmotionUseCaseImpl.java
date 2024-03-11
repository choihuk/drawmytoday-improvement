package tipitapi.drawmytodayimprovement.domain.diary.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetActiveEmotionResponse;
import tipitapi.drawmytodayimprovement.service.EmotionService;
import tipitapi.drawmytodayimprovement.service.UserValidator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmotionUseCaseImpl implements EmotionUseCase {

    private final UserValidator userValidator;
    private final EmotionService emotionService;

    @Override
    @Transactional(readOnly = true)
//    @Cacheable(CacheConst.ACTIVE_EMOTIONS)
    public List<GetActiveEmotionResponse> getActiveEmotions(Long userId) {
        userValidator.validateByUserId(userId);
        List<Emotion> activeEmotions = emotionService.getActiveEmotions();
        return activeEmotions.stream()
                .map(GetActiveEmotionResponse::from)
                .toList();
    }
}
