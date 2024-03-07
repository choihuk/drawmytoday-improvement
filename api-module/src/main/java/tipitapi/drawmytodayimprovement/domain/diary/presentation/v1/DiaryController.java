package tipitapi.drawmytodayimprovement.domain.diary.presentation.v1;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.DiaryUseCase;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.request.CreateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.request.RegenerateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.request.UpdateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.*;
import tipitapi.drawmytodayimprovement.imagegenerator.ImageGeneratorException;
import tipitapi.drawmytodayimprovement.utils.DateUtils;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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
        var response = diaryUseCase.createDiary(
                tokenInfo.userId(),
                request.emotionId(),
                request.toCreateDiaryElement()
        );
        return SuccessResponse.of(response).asHttp(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/regenerate")
    public ResponseEntity<Void> regenerateDiaryImage(
            @AuthUser JwtTokenInfo tokenInfo,
            @Parameter(description = "일기 id", in = ParameterIn.PATH) @PathVariable("id") Long diaryId,
            @RequestBody RegenerateDiaryRequest request
    ) throws ImageGeneratorException {
        diaryUseCase.regenerateDiaryImage(tokenInfo.userId(), diaryId, request.diary());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<GetDiaryResponse>> getDiary(
            @PathVariable("id") Long diaryId,
            @AuthUser JwtTokenInfo jwtTokenInfo
    ) {
        var response = diaryUseCase.getDiary(jwtTokenInfo.userId(), diaryId);
        return SuccessResponse.of(response).asHttp(HttpStatus.OK);
    }

    @Override
    @GetMapping("/calendar/monthly")
    public ResponseEntity<SuccessResponse<List<GetMonthlyDiaryResponse>>> getMonthlyDiaries(
            @RequestParam("year") int year, @RequestParam("month") int month,
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        var response = diaryUseCase.getMonthlyDiaries(tokenInfo.userId(), year, month);
        return SuccessResponse.of(response).asHttp(HttpStatus.OK);
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

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(
            @AuthUser JwtTokenInfo tokenInfo,
            @PathVariable("id") Long diaryId
    ) {
        diaryUseCase.deleteDiary(tokenInfo.userId(), diaryId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/calendar/date")
    public ResponseEntity<SuccessResponse<GetDiaryExistByDateResponse>> getDiaryExistByDate(
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day,
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        LocalDate diaryDate = DateUtils.getDate(year, month, day);
        return SuccessResponse.of(
                diaryUseCase.getDiaryExistByDate(tokenInfo.userId(), diaryDate)
        ).asHttp(HttpStatus.OK);
    }

    @Override
    @GetMapping("/last-creation")
    public ResponseEntity<SuccessResponse<GetLastCreationResponse>> getLastCreation(
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        return SuccessResponse.of(
                diaryUseCase.getLastCreation(tokenInfo.userId())
        ).asHttp(HttpStatus.OK);
    }

    /**
     * 가능하다면 API를 Ticket 도메인으로 이동하는게 맞을거 같음(유저가 티켓이 있는지를 확인하는 API이기 때문)
     */
    @Override
    @GetMapping("/limit")
    public ResponseEntity<SuccessResponse<GetDiaryLimitResponse>> getDrawLimit(
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        return SuccessResponse.of(
                diaryUseCase.getDrawLimit(tokenInfo.userId())
        ).asHttp(HttpStatus.OK);
    }
}
