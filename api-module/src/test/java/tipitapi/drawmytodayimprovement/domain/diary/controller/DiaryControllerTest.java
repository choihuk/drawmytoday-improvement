package tipitapi.drawmytodayimprovement.domain.diary.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tipitapi.drawmytodayimprovement.testdata.TestDiary.*;
import static tipitapi.drawmytodayimprovement.testdata.TestEmotion.*;
import static tipitapi.drawmytodayimprovement.testdata.TestImage.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import tipitapi.drawmytodayimprovement.DiaryUseCase;
import tipitapi.drawmytodayimprovement.component.AllDiaryElement;
import tipitapi.drawmytodayimprovement.component.Diary;
import tipitapi.drawmytodayimprovement.component.Emotion;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.component.Prompt;
import tipitapi.drawmytodayimprovement.config.ControllerTestSetup;
import tipitapi.drawmytodayimprovement.config.WithCustomUser;

@WebMvcTest(DiaryController.class)
@WithCustomUser
class DiaryControllerTest extends ControllerTestSetup {

	private static final String BASIC_URL = "/diary";

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
					.willReturn(AllDiaryElement.of(diary, emotion, prompt, List.of(image)));

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