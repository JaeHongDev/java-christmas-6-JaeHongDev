package christmas.domain.benefit;

import static christmas.domain.benefit.DiscountCondition.CHRISTMAS_DISCOUNT;
import static christmas.domain.benefit.DiscountCondition.SPECIAL_DISCOUNT;
import static christmas.domain.benefit.DiscountCondition.WEEKDAY_DISCOUNT;
import static christmas.domain.benefit.DiscountCondition.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.util.ChristmasLocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DiscountConditionTest {

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void 크리스마스_할인_조건은_크리스마스_이후에는_유효하지_않습니다(int input) {
        final var date = ChristmasLocalDate.create(input);

        assertThat(CHRISTMAS_DISCOUNT.isSatisfy(date)).isFalse();
    }


    @Test
    void 크리스마스_할인_조건은_크리스마스_이전에만_적용됩니다() {
        IntStream.rangeClosed(1, 25)
                .mapToObj(ChristmasLocalDate::create)
                .forEach((date) -> assertThat(CHRISTMAS_DISCOUNT.isSatisfy(date)).isTrue());
    }

    @Test
    void 평일_할인_규칙은_평일에만_조건이_성립합니다() {
        final var weekend = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
        final var weekDay = IntStream.rangeClosed(1, 31)
                .filter(day -> !weekend.contains(day))
                .boxed()
                .toList();

        assertAll(
                () -> weekDay.stream()
                        .map(day -> LocalDate.of(2023, 12, day))
                        .forEach((date) -> assertThat(WEEKDAY_DISCOUNT.isSatisfy(date)).isTrue()),
                () -> weekend.stream()
                        .map(day -> LocalDate.of(2023, 12, day))
                        .forEach((date) -> assertThat(WEEKDAY_DISCOUNT.isSatisfy(date)).isFalse()));
    }

    @Test
    void 주말_할인_규칙은_주말에만_조건이_성립합니다() {
        final var weekend = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
        final var weekDay = IntStream.rangeClosed(1, 31)
                .filter(day -> !weekend.contains(day))
                .boxed()
                .toList();

        assertAll(
                () -> weekDay.stream()
                        .map(ChristmasLocalDate::create)
                        .forEach((date) -> assertThat(WEEKEND_DISCOUNT.isSatisfy(date)).isFalse()),
                () -> weekend.stream()
                        .map(ChristmasLocalDate::create)
                        .forEach((date) -> assertThat(WEEKEND_DISCOUNT.isSatisfy(date)).isTrue()));
    }

    @Test
    void 특별할인은_특별한날에만_적용이_됩니다() {

        final var specialDay = List.of(3, 10, 17, 24, 25, 31);
        final var notSpecialDay = IntStream.rangeClosed(1, 31)
                .filter(day -> !specialDay.contains(day))
                .boxed()
                .toList();
        assertAll(
                () -> specialDay.stream()
                        .map(day -> LocalDate.of(2023, 12, day))
                        .forEach((date) -> assertThat(SPECIAL_DISCOUNT.isSatisfy(date)).isTrue()),
                () -> notSpecialDay.stream()
                        .map(day -> LocalDate.of(2023, 12, day))
                        .forEach((date) -> assertThat(SPECIAL_DISCOUNT.isSatisfy(date)).isFalse())
        );
    }
}