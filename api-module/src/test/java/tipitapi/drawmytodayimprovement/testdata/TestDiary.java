package tipitapi.drawmytodayimprovement.testdata;

import tipitapi.drawmytodayimprovement.domain.Diary;

import java.time.LocalDateTime;

public class TestDiary {
    public static Diary createTestDiary(Long diaryId, Long userId) {
        return Diary.builder()
                .diaryId(diaryId)
                .userId(userId)
                .emotionId(1L)
                .title("title")
                .diaryDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
