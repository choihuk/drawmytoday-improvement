package tipitapi.drawmytodayimprovement.image.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;

@SQLDelete(sql = "UPDATE image SET deleted_at = current_timestamp WHERE image_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "image")
public class ImageEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id", nullable = false)
	private DiaryEntity diary;

	@NotNull
	@Column(nullable = false)
	private String imageUrl;

	@NotNull
	private boolean isSelected;

	private String review;

	private LocalDateTime deletedAt;

	@Builder
	private ImageEntity(Long id, DiaryEntity diary, String imageUrl, boolean isSelected, String review,
		LocalDateTime deletedAt) {
		this.id = id;
		this.diary = diary;
		this.imageUrl = imageUrl;
		this.isSelected = isSelected;
		this.review = review;
		this.deletedAt = deletedAt;
	}
}

