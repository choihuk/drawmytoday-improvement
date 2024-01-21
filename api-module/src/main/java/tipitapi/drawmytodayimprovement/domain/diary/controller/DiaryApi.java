package tipitapi.drawmytodayimprovement.domain.diary.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.domain.diary.request.CreateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

@Tag(name = "Diary", description = "일기 API")
public interface DiaryApi {
	@Operation(summary = "일기 생성", description = "DALL-E API를 사용하여 이미지를 발급하여 일기를 생성한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "201",
			description = "성공적으로 생성된 일기 정보"),
		@ApiResponse(
			responseCode = "400",
			description = "U004 : 이미 그림일기를 그린 유저입니다.",
			content = @Content(schema = @Schema(hidden = true))),
		@ApiResponse(
			responseCode = "404",
			description = "E001 : 감정을 찾을 수 없습니다.",
			content = @Content(schema = @Schema(hidden = true))),
		@ApiResponse(
			responseCode = "409",
			description = "D001 : 이미 일기를 그린 날짜입니다.",
			content = @Content(schema = @Schema(hidden = true))),
		@ApiResponse(
			responseCode = "404",
			description = "T001 : 유효한 티켓이 존재하지 않습니다.",
			content = @Content(schema = @Schema(hidden = true))),
		@ApiResponse(
			responseCode = "500",
			description = "R003 : R2 처리에 실패하였습니다.",
			content = @Content(schema = @Schema(hidden = true))),
	})
	@PostMapping
	ResponseEntity<SuccessResponse<CreateDiaryResponse>> createDiary(
		@RequestBody @Valid CreateDiaryRequest request,
		@AuthUser JwtTokenInfo tokenInfo
	);

	@Operation(summary = "일기 조회", description = "특정 일기의 내용을 반환한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "일기 상세 정보"),
		@ApiResponse(
			responseCode = "403",
			description = "D002 : 자신의 일기에만 접근할 수 있습니다.",
			content = @Content(schema = @Schema(hidden = true))),
		@ApiResponse(
			responseCode = "404",
			description = "D001 : 일기를 찾을 수 없습니다.\t\nI001 : 선택된 이미지를 찾을 수 없습니다.",
			content = @Content(schema = @Schema(hidden = true))),
	})
	@GetMapping("/{id}")
	ResponseEntity<SuccessResponse<GetDiaryResponse>> getDiary(
		@Parameter(description = "일기 id", in = ParameterIn.PATH) @PathVariable("id") Long diaryId,
		@AuthUser JwtTokenInfo jwtTokenInfo
	);
}
