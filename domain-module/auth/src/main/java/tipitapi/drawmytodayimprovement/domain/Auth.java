package tipitapi.drawmytodayimprovement.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Auth {

    private Long authId;
    private LocalDateTime createdAt;
    private Long userId;
    private String refreshToken;

    public static Auth create(Long userId, String refreshToken) {
        return Auth.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .build();
    }
}
