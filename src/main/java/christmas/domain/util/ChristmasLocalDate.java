package christmas.domain.util;

import java.time.LocalDate;

public class ChristmasLocalDate {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    public static LocalDate create(int day) {
        return LocalDate.of(YEAR, MONTH, day);
    }
}
