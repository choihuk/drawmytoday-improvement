package tipitapi.drawmytodayimprovement.domain.diary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.response.SuccessResponse;
import tipitapi.drawmytodayimprovement.domain.vo.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.domain.diary.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.usecase.DiaryUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

	private final DiaryUseCase diaryUseCase;

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<GetDiaryResponse>> getDiary(
		@PathVariable("id") Long diaryId,
		@AuthUser JwtTokenInfo jwtTokenInfo
	) {
		return SuccessResponse.of(
			GetDiaryResponse.of(diaryUseCase.getDiary(jwtTokenInfo.getUserId(), diaryId))
		).asHttp(HttpStatus.OK);
	}

}
