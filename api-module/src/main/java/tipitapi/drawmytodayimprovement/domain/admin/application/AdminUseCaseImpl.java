package tipitapi.drawmytodayimprovement.domain.admin.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tipitapi.drawmytodayimprovement.domain.admin.application.response.GetImageAdminResponse;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;
import tipitapi.drawmytodayimprovement.service.ImageService;
import tipitapi.drawmytodayimprovement.service.UserValidator;

@Component
@RequiredArgsConstructor
public class AdminUseCaseImpl implements AdminUseCase {

    private final UserValidator userValidator;
    private final ImageService imageService;

    public PageResponse<GetImageAdminResponse> getImagesForMonitoring(Long userId, PageableRequest pageableRequest,
                                                                      Long emotionId, boolean withTest) {
        userValidator.validateAdmin(userId);
        return imageService.getImagesForMonitoring(pageableRequest, emotionId, withTest)
                .map(GetImageAdminResponse::from);
    }
}
