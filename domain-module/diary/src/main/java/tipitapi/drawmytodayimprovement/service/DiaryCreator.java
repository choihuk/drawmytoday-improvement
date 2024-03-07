package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;
import tipitapi.drawmytodayimprovement.storage.ImageUploadService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryCreator {

    private final DiaryRepository diaryRepository;
    private final PromptService promptService;
    private final ImageUploadService r2Service;
    private final ImageService imageService;

    @Value("${spring.profiles.active:Unknown}")
    private String profile;


    public Long saveAfterCreateDiary(Long userId, Long emotionId, CreateDiaryElement createDiaryElement,
                                     Prompt prompt, List<byte[]> images) {
        Diary diary = diaryRepository.save(Diary.create(userId, emotionId, createDiaryElement));
        prompt = promptService.save(prompt);

        String imagePath = createImagePath(diary.getDiaryId());
        for (int i = 0; i < images.size(); i++) {
            boolean isSelected = i == 0;
            r2Service.uploadImage(images.get(i), imagePath);
            imageService.save(Image.create(diary.getDiaryId(), prompt.getPromptId(), imagePath, isSelected));
        }

        return diary.getDiaryId();
    }

    private String createImagePath(Long diaryId) {
        return String.format(profile + "/post/%d/%s_%d.webp", diaryId, new Date().getTime(), 1);
    }

    public void save(Diary diary) {
        diaryRepository.save(diary);
    }

    public void saveAfterRegenerateDiary(Long diaryId, Prompt prompt, List<byte[]> generatedImages) {
        prompt = promptService.save(prompt);

        String imagePath = createImagePath(diaryId);
        imageService.unSelectAllImage(diaryId);
        for (int i = 0; i < generatedImages.size(); i++) {
            boolean isSelected = i == 0;
            r2Service.uploadImage(generatedImages.get(i), imagePath);
            imageService.save(Image.create(diaryId, prompt.getPromptId(), imagePath, isSelected));
        }
    }
}
