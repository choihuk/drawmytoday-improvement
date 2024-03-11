package tipitapi.drawmytodayimprovement.domain.diary.application.usecase;

import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetActiveEmotionResponse;

import java.util.List;

public interface EmotionUseCase {
    List<GetActiveEmotionResponse> getActiveEmotions(Long userId);
}
