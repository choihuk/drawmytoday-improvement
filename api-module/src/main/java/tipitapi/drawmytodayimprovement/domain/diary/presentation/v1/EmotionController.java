package tipitapi.drawmytodayimprovement.domain.diary.presentation.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.EmotionUseCase;
import tipitapi.drawmytodayimprovement.domain.diary.application.usecase.response.GetActiveEmotionResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emotions")
public class EmotionController implements EmotionApi {

    private final EmotionUseCase emotionUseCase;

    @Override
    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<GetActiveEmotionResponse>>> getActiveEmotionResponses(
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        return SuccessResponse.of(
                emotionUseCase.getActiveEmotions(tokenInfo.userId())
        ).asHttp(HttpStatus.OK);
    }
}
