package tipitapi.drawmytodayimprovement.diary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.common.response.SuccessResponse;
import tipitapi.drawmytodayimprovement.diary.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.usecase.DiaryUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

	private final DiaryUseCase diaryUseCase;

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<GetDiaryResponse>> getDiary(
		@PathVariable("id") Long diaryId
		// @AuthUser JwtTokenInfo tokenInfo
	) {
		return SuccessResponse.of(
			GetDiaryResponse.of(diaryUseCase.getDiary(1L, diaryId))
		).asHttp(HttpStatus.OK);
	}

}
