package tipitapi.drawmytodayimprovement.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static LocalDateTime getStartDate(int year, int month) {
        validateMonth(month);
        return LocalDateTime.of(year, month, 1, 0, 0, 0);
    }

    public static LocalDateTime getEndDate(int year, int month) {
        validateMonth(month);
        return LocalDateTime.of(year, month, getMaxDay(month), 23, 59);
    }

    public static LocalDate getDate(int year, int month, int day) {
        validateDate(month, day);
        return LocalDate.of(year, month, day);
    }

    private static void validateMonth(int month) {
        if (month > 12 || month < 1) {
            throw new BusinessException(CommonErrorCode.INVALID_INPUT_VALUE);
        }
    }

    private static void validateDate(int month, int day) {
        validateMonth(month);
        if (day > getMaxDay(month) || day < 1) {
            throw new BusinessException(CommonErrorCode.INVALID_INPUT_VALUE);
        }
    }

    private static int getMaxDay(int month) {
        if (month == 2) {
            return 29;
        }
        if (Stream.of(1, 3, 5, 7, 8, 10, 12)
                .anyMatch(m -> m == month)) {
            return 31;
        } else {
            return 30;
        }
    }
}
