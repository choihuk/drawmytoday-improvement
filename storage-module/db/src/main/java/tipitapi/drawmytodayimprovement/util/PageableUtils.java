package tipitapi.drawmytodayimprovement.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;

public class PageableUtils {
    public static Pageable toPageable(PageableRequest pageableRequest) {
        return Pageable.ofSize(pageableRequest.size())
                .withPage(pageableRequest.page());
    }

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page.getNumber(), page.getSize(), (int) page.getTotalElements(), page.getContent());
    }
}
