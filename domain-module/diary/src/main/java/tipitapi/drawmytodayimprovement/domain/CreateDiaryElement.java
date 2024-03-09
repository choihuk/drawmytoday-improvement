package tipitapi.drawmytodayimprovement.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateDiaryElement(String diaryNote,
                                 String notes,
                                 LocalDate diaryDate,
                                 LocalTime userTime) {

    public CreateDiaryElement {
        if (userTime == null) {
            userTime = LocalTime.now();
        }
    }

}
