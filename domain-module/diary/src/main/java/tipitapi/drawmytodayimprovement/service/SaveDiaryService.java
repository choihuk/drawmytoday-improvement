package tipitapi.drawmytodayimprovement.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.repository.DiaryRepository;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;
import tipitapi.drawmytodayimprovement.storage.ImageUploadService;

@Service
@RequiredArgsConstructor
public class SaveDiaryService {

	private final DiaryRepository diaryRepository;
	private final PromptService promptService;
	private final ImageUploadService r2Service;
	private final ImageRepository imageRepository;

	@Value("${spring.profiles.active:Unknown}")
	private String profile;

	@Transactional
	public Long saveAll(Long userId, Long emotionId, CreateDiaryElement diaryElement, LocalTime userTime,
		String promptText, List<byte[]> images) {
		LocalDateTime diaryDateTime = diaryElement.diaryDate().atTime(userTime);
		Diary diary = diaryRepository.save(Diary.create(userId, emotionId, diaryDateTime, diaryElement.notes()));

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
}
