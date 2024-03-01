package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;
import tipitapi.drawmytodayimprovement.storage.ImageUploadService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryCreator {

    private final DiaryRepository diaryRepository;
    private final PromptService promptService;
    private final ImageUploadService r2Service;
    private final ImageRepository imageRepository;

    @Value("${spring.profiles.active:Unknown}")
    private String profile;


    public Long saveAfterCreateDiary(Long userId, Long emotionId, CreateDiaryElement createDiaryElement,
                                     String promptText, List<byte[]> images) {
        Diary diary = diaryRepository.save(Diary.create(userId, emotionId, createDiaryElement));

        promptService.createSuccessPrompt(diary, promptText);

        String imagePath = String.format(profile + "/post/%d/%s_%d.webp", diary.getDiaryId(),
                new Date().getTime(), 1);
        for (int i = 0; i < images.size(); i++) {
            boolean isSelected = i == 0;
            r2Service.uploadImage(images.get(i), imagePath);
            imageRepository.save(Image.create(diary.getDiaryId(), imagePath, isSelected));
        }

        return diary.getDiaryId();
    }

    public void save(Diary diary) {
        diaryRepository.save(diary);
    }
}
