package christmas.domain.benefit.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.vo.Food;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 평일 할인")
class WeekDayDiscountTest {

    @Test
    void 평일_할인은_디저트_메뉴_수량_1개당_2023원_할인됩니다() {
        final var result = new WeekDayDiscount().apply(LocalDate.of(2023, 12, 1), new OrderLine(
                List.of(
                        new OrderItem(Food.ICE_CREAM, 10),
                        new OrderItem(Food.MUSHROOM_SOUP, 10)
                )
        ));
        assertThat(result).isEqualTo(new Payment(10 * 2023));
    }

}