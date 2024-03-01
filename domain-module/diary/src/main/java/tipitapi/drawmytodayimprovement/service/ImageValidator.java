package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.MonthlyDiary;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageValidator {

    private final ImageRepository imageRepository;

    public void validateMonthlyDiariesImages(List<MonthlyDiary> monthlyDiaries) {
    }
}
