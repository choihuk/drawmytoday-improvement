package tipitapi.drawmytodayimprovement.domain.diary.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.DiaryUseCase;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.domain.diary.request.CreateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.request.UpdateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.response.GetMonthlyDiaryResponse;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController implements DiaryApi {

	private final DiaryUseCase diaryUseCase;

	@Override
	@PostMapping
	public ResponseEntity<SuccessResponse<CreateDiaryResponse>> createDiary(
		@RequestBody @Valid CreateDiaryRequest request,
		@AuthUser JwtTokenInfo tokenInfo
	) {
		Long diaryId = diaryUseCase.createDiary(tokenInfo.userId(), request.emotionId(),
			request.toCreateDiaryElement());

		return SuccessResponse.of(
			CreateDiaryResponse.of(diaryId)
		).asHttp(HttpStatus.CREATED);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<GetDiaryResponse>> getDiary(
		@PathVariable("id") Long diaryId,
		@AuthUser JwtTokenInfo jwtTokenInfo
	) {
		return SuccessResponse.of(
			GetDiaryResponse.of(diaryUseCase.getDiary(jwtTokenInfo.userId(), diaryId))
		).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/calendar/monthly")
	public ResponseEntity<SuccessResponse<List<GetMonthlyDiaryResponse>>> getMonthlyDiaries(
		@RequestParam("year") int year, @RequestParam("month") int month,
		@AuthUser JwtTokenInfo tokenInfo
	) {
		List<GetMonthlyDiaryResponse> monthlyDiaryResponses =
			diaryUseCase.getMonthlyDiaries(tokenInfo.userId(), year, month)
				.stream()
				.map(GetMonthlyDiaryResponse::of)
				.toList();
		return SuccessResponse.of(monthlyDiaryResponses)
			.asHttp(HttpStatus.OK);
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateDiaryNotes(
		@RequestBody @Valid UpdateDiaryRequest updateDiaryRequest,
		@PathVariable("id") Long diaryId,
		@AuthUser JwtTokenInfo tokenInfo
	) {
		diaryUseCase.updateDiaryNotes(tokenInfo.userId(), diaryId, updateDiaryRequest.notes());
		return ResponseEntity.noContent().build();
	}
}
