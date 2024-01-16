package tipitapi.drawmytodayimprovement.prompt.entity;

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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.BaseEntity;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "prompt")
public class PromptEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prompt_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id", nullable = true)
	private DiaryEntity diary;

	@NotNull
	@Column(length = 1100)
	private String promptText;

	@NotNull
	private boolean isSuccess;

	@Builder
	private PromptEntity(Long id, DiaryEntity diary, String promptText, boolean isSuccess) {
		this.id = id;
		this.diary = diary;
		this.promptText = promptText;
		this.isSuccess = isSuccess;
	}
}
