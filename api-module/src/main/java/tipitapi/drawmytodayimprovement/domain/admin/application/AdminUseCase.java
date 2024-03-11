package tipitapi.drawmytodayimprovement.domain.admin.application;

import tipitapi.drawmytodayimprovement.domain.admin.application.response.GetImageAdminResponse;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;

public interface AdminUseCase {
    PageResponse<GetImageAdminResponse> getImagesForMonitoring(Long userId, PageableRequest pageableRequest,
                                                               Long emotionId, boolean withTest);
}
