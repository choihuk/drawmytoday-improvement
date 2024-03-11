package tipitapi.drawmytodayimprovement.table.adreward.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.table.user.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ad_reward")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdRewardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_reward_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private LocalDateTime usedAt;

    @Builder
    private AdRewardEntity(Long id, UserEntity user, LocalDateTime usedAt) {
        this.id = id;
        this.user = user;
        this.usedAt = usedAt;
    }
}
