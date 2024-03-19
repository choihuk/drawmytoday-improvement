package tipitapi.drawmytodayimprovement.domain.diary.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;
import tipitapi.drawmytodayimprovement.config.ControllerTestSetup;
import tipitapi.drawmytodayimprovement.config.WithCustomUser;
import tipitapi.drawmytodayimprovement.domain.Diary;
import tipitapi.drawmytodayimprovement.domain.Emotion;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.domain.Prompt;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.DiaryUseCase;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.DiaryController;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tipitapi.drawmytodayimprovement.testdata.TestDiary.createTestDiary;
import static tipitapi.drawmytodayimprovement.testdata.TestEmotion.createTestEmotion;
import static tipitapi.drawmytodayimprovement.testdata.TestImage.createTestImage;

@WebMvcTest(DiaryController.class)
@WithCustomUser
class DiaryControllerTest extends ControllerTestSetup {

	private static final String BASIC_URL = "/translatedDiary";

	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	DiaryUseCase diaryUseCase;

	@Nested
	@DisplayName("getDiary 메서드는")
	class GetDiaryTest {

		@Nested
		@DisplayName("파라미터로 받은 diaryId에 해당하는 일기가")
		class If_diaryId_is {

			@Test
			@DisplayName("존재하고, 요청한 유저의 일기라면 OK 상태코드와 일기 아이디,이미지 url,"
					+ " 일기 작성일, 일기 생성일, 감정, 일기 내용, 프롬프트를 응답한다.")
			void exist_than_return_diary() throws Exception {
				// given
				long diaryId = 1L;

				Diary diary = createTestDiary(diaryId, REQUEST_USER_ID);
				Emotion emotion = createTestEmotion(1L);
				Prompt prompt = Prompt.createEmptyPrompt();
				Image image = createTestImage(1L, diaryId);
				given(diaryUseCase.getDiary(REQUEST_USER_ID, diaryId))
						.willReturn(GetDiaryResponse.of(diary, emotion, prompt, List.of(image), selectedImage));

				// when
				ResultActions result = mockMvc.perform(get(BASIC_URL + "/" + diaryId));

				// then
				result.andExpect(status().isOk())
						.andExpect(jsonPath("$.data.id").value(diaryId))
						.andExpect(jsonPath("$.data.imageUrl").value(image.getImageUrl()))
						.andExpect(jsonPath("$.data.date").value(
								parseLocalDateTime(diary.getDiaryDate())))
						.andExpect(jsonPath("$.data.createdAt").value(
								parseLocalDateTime(diary.getCreatedAt())))
						.andExpect(jsonPath("$.data.emotion").value(emotion.getEmotionPrompt()))
						.andExpect(jsonPath("$.data.notes").value(diary.getNotes()))
						.andExpect(jsonPath("$.data.prompt").value(prompt.getPromptText()));
			}
		}
	}

}