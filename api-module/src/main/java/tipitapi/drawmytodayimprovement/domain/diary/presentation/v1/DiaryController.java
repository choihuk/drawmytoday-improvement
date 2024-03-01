package tipitapi.drawmytodayimprovement.domain.diary.presentation.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.DiaryUseCase;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.request.CreateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.request.UpdateDiaryRequest;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.CreateDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetDiaryExistByDateResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetDiaryResponse;
import tipitapi.drawmytodayimprovement.domain.diary.presentation.v1.response.GetMonthlyDiaryResponse;
import tipitapi.drawmytodayimprovement.utils.DateUtils;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

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
}
