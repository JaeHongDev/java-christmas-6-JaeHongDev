package christmas.domain.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class ChristmasLocalDate {

    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    private static final LocalDate CHRISTMAS = create(25);

    private static final List<Integer> CHRISTMAS_SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);

    public static LocalDate create(int day) {
        return LocalDate.of(YEAR, MONTH, day);
    }


    public static boolean containsSpecialDay(LocalDate date) {
        return CHRISTMAS_SPECIAL_DAY.contains(date.getDayOfMonth());
    }

    public static boolean beforeChristmas(LocalDate localDate) {
        return CHRISTMAS.isEqual(localDate) || localDate.isBefore(CHRISTMAS);
    }

    public static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public static boolean isWeekday(LocalDate date) {
        return !isWeekend(date);
    }

}
