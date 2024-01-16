package tipitapi.drawmytodayimprovement.usecase;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.CreateDiaryElement;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.exception.ImageGeneratorException;
import tipitapi.drawmytodayimprovement.service.ImageGeneratorService;
import tipitapi.drawmytodayimprovement.service.PromptService;
import tipitapi.drawmytodayimprovement.service.PromptTextService;
import tipitapi.drawmytodayimprovement.service.SaveDiaryService;
import tipitapi.drawmytodayimprovement.service.UserService;
import tipitapi.drawmytodayimprovement.service.ValidateDiaryService;
import tipitapi.drawmytodayimprovement.service.ValidateEmotionService;
import tipitapi.drawmytodayimprovement.service.ValidateUserService;

@Component
@RequiredArgsConstructor
public class DiaryUseCase {

	private final ValidateDiaryService validateDiaryService;
	private final ValidateUserService validateUserService;
	private final ValidateEmotionService validateEmotionService;
	private final PromptTextService promptTextService;
	private final ImageGeneratorService karloService;
	private final PromptService promptService;
	private final SaveDiaryService saveDiaryService;
	private final UserService userService;

	@Transactional(readOnly = true)
	public Diary getDiary(Long userId, Long diaryId) {
		validateUserService.validateByUserId(userId);
		return validateDiaryService.validateDiaryById(diaryId, userId);
	}

	public Long createDiary(Long userId, Long emotionId, CreateDiaryElement diaryElement, LocalTime userTime) {
		validateUserService.validateByUserId(userId);
		Emotion emotion = validateEmotionService.validateById(emotionId);
		validateDiaryService.validateDiaryNotExist(userId, diaryElement.diaryDate());
		validateUserService.validateDrawable(userId);

		String promptText = promptTextService.createPromptText(emotion, diaryElement.keyword());
		List<byte[]> generatedImages = null;
		try {
			generatedImages = karloService.generateImage(promptText);
		} catch (ImageGeneratorException e) {
			promptService.createFailedPrompt(promptText);
			throw e;
		}

		Long diaryId = saveDiaryService.saveAll(userId, emotionId, diaryElement, userTime, promptText, generatedImages);

		userService.useTicket(userId);
		return diaryId;
	}
}
