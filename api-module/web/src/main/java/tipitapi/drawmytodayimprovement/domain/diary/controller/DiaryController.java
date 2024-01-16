package tipitapi.drawmytodayimprovement.domain.diary.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.response.SuccessResponse;
import tipitapi.drawmytodayimprovement.domain.diary.request.CreateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.usecase.DiaryUseCase;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

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
			GetDiaryResponse.of(diaryUseCase.getDiary(jwtTokenInfo.userId(), diaryId))
		).asHttp(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SuccessResponse<CreateDiaryResponse>> createDiary(
		@RequestBody @Valid CreateDiaryRequest request,
		@AuthUser JwtTokenInfo tokenInfo) {
		Long diaryId = diaryUseCase.createDiary(tokenInfo.userId(), request.emotionId(),
			request.toCreateDiaryElement(), request.userTime());

		return SuccessResponse.of(
			CreateDiaryResponse.of(diaryId)
		).asHttp(HttpStatus.CREATED);
	}
}
