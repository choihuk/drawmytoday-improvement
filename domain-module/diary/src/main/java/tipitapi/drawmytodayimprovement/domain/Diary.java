package tipitapi.drawmytodayimprovement.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Diary {

    private static final DiaryEncryptor encryptor = new DiaryEncryptor();

    private Long diaryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long emotionId;
    private Long userId;
    private LocalDateTime diaryDate;
    private String notes;
    private String title;
    private String weather;
    private boolean isAi;
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

    public static Diary create(Long userId, Long emotionId, CreateDiaryElement createDiaryElement) {
        LocalDateTime diaryDateTime = createDiaryElement.diaryDate()
                .atTime(createDiaryElement.userTime());
        return Diary.builder()
                .userId(userId)
                .emotionId(emotionId)
                .diaryDate(diaryDateTime)
                .notes(encryptor.encrypt(createDiaryElement.notes()))
                .isAi(true)
                .isTest(false)
                .build();
    }

    public String getNotes() {
        return encryptor.decrypt(notes);
    }

    /**
     * @note mapper에 제공을 목적으로 만든 메서드
     */
    public String getRowNotes() {
        return notes;
    }

    public void updateNotes(String notes) {
        this.notes = encryptor.encrypt(notes);
    }
}
