package christmas.domain.benefit;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public enum DiscountCondition {
    CHRISTMAS_DISCOUNT(date -> date.isBefore(LocalDate.of(2023, 12, 26))),
    WEEKEND_DISCOUNT(date -> date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY),
    WEEKDAY_DISCOUNT(date -> date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY),
    SPECIAL_DISCOUNT(date -> List.of(3, 10, 17, 24, 31).contains(date.getDayOfMonth())),
    ALL_DAY(ignore -> true);
    private final Predicate<LocalDate> function;

    DiscountCondition(Predicate<LocalDate> function) {
        this.function = function;
    }

    public boolean isSatisfy(LocalDate date) {
        return function.test(date);
    }
}
