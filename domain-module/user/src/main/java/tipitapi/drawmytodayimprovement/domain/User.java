package tipitapi.drawmytodayimprovement.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private SocialCode socialCode;
    private LocalDateTime lastDiaryDate;
    private UserRole userRole;
    private LocalDateTime deletedAt;

    public static User create(String email, SocialCode socialCode) {
        return User.builder()
                .email(email)
                .socialCode(socialCode)
                .userRole(UserRole.USER)
                .build();
    }
}
