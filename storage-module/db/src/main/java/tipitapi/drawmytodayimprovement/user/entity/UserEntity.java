package tipitapi.drawmytodayimprovement.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntityWithUpdate;
import tipitapi.drawmytodayimprovement.domain.enumeration.SocialCode;
import tipitapi.drawmytodayimprovement.domain.enumeration.UserRole;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at is null")
public class UserEntity extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SocialCode socialCode;

	private LocalDateTime lastDiaryDate;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	private LocalDateTime deletedAt;

	@Builder(access = AccessLevel.PRIVATE)
	private UserEntity(String email, SocialCode socialCode) {
		this.email = email;
		this.socialCode = socialCode;
		this.userRole = UserRole.USER;
	}

}