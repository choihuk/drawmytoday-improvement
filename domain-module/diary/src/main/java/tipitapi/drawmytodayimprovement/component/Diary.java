package tipitapi.drawmytodayimprovement.component;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Diary {

	private static final DiaryEncryptor encryptor = new DiaryEncryptor();

	private Long diaryId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long emotionId;
	private Long userId;
	private LocalDateTime diaryDate;
	private String notes;
	private boolean isAi;
	private String title;
	private String weather;
	private LocalDateTime deletedAt;
	private boolean isTest;

	@Builder
	private Diary(Long diaryId, LocalDateTime createdAt, LocalDateTime updatedAt, Long emotionId,
		Long userId, LocalDateTime diaryDate, String notes, boolean isAi, String title, String weather,
		LocalDateTime deletedAt, boolean isTest) {
		this.diaryId = diaryId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.emotionId = emotionId;
		this.userId = userId;
		this.diaryDate = diaryDate;
		this.notes = notes;
		this.isAi = isAi;
		this.title = title;
		this.weather = weather;
		this.deletedAt = deletedAt;
		this.isTest = isTest;
	}

	public static Diary create(Long userId, Long emotionId, LocalDateTime diaryDateTime, String notes) {
		return Diary.builder()
			.userId(userId)
			.emotionId(emotionId)
			.diaryDate(diaryDateTime)
			.notes(encryptor.encrypt(notes))
			.isAi(true)
			.isTest(false)
			.build();
	}

	public String getNotes() {
		return encryptor.decrypt(notes);
	}

	/**
	 * mapper에 제공을 목적으로 만든 메서드
	 */
	public String getRowNotes() {
		return notes;
	}
}
