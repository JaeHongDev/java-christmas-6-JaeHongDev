package christmas.domain.benefit;

import christmas.domain.util.ChristmasLocalDate;
import java.time.LocalDate;
import java.util.function.Predicate;

public enum DiscountCondition {

    CHRISTMAS_DISCOUNT(ChristmasLocalDate::beforeChristmas),
    WEEKEND_DISCOUNT(ChristmasLocalDate::isWeekend),
    WEEKDAY_DISCOUNT(ChristmasLocalDate::isWeekday),
    SPECIAL_DISCOUNT(ChristmasLocalDate::containsSpecialDay),
    ALL_DAY(ignore -> true);
    private final Predicate<LocalDate> predicate;

    DiscountCondition(Predicate<LocalDate> predicate) {
        this.predicate = predicate;
    }

    public boolean isSatisfy(LocalDate date) {
        return predicate.test(date);
    }

}
