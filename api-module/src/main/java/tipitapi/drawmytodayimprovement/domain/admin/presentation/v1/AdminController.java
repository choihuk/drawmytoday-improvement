package tipitapi.drawmytodayimprovement.domain.admin.presentation.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.resolver.AuthUser;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenInfo;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenProvider;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtType;
import tipitapi.drawmytodayimprovement.common.utils.HeaderUtils;
import tipitapi.drawmytodayimprovement.domain.admin.application.AdminUseCase;
import tipitapi.drawmytodayimprovement.domain.admin.application.response.GetImageAdminResponse;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final AdminUseCase adminUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 일기가 아닌 이미지가 더 맞는 표현이기에 uri를 제외한 네이밍을 변경
     */
    @Override
    @GetMapping("/admin/diaries")
    public ResponseEntity<SuccessResponse<PageResponse<GetImageAdminResponse>>> getImagesForMonitoring(
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction,
            @RequestParam(name = "emotion", required = false) Long emotionId,
            @RequestParam(name = "with_test", required = false, defaultValue = "true") boolean withTest,
            @AuthUser JwtTokenInfo tokenInfo
    ) {
        PageableRequest pageableRequest = PageableRequest.of(page, size, direction);
        return SuccessResponse.of(
                adminUseCase.getImagesForMonitoring(tokenInfo.userId(), pageableRequest, emotionId, withTest)
        ).asHttp(HttpStatus.OK);
    }

    @Override
    @GetMapping("/dev/expire")
    public String getExpiredJwt(HttpServletRequest request) {
        String jwtToken = HeaderUtils.getJwtToken(request, JwtType.BOTH);
        return jwtTokenProvider.expireToken(jwtToken);
    }
}
