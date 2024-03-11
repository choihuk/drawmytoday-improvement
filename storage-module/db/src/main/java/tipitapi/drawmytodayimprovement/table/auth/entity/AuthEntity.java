package tipitapi.drawmytodayimprovement.table.auth.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @NotNull
    @Column(nullable = false)
    private String refreshToken;

    @Builder
    private AuthEntity(Long id, UserEntity userEntity, String refreshToken) {
        this.id = id;
        this.userEntity = userEntity;
        this.refreshToken = refreshToken;
    }

}
