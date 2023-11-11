package christmas.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
@DisplayName("[도메인] 주문 테스트")
class OrderTest {

    @Test
    void 할인_전_금액을_가지고_옵니다() {
        final var order = new Order(LocalDate.of(2023, 12, 1),
                new OrderLine(List.of(
                        new OrderItem(Food.BBQ_RIBS, 10),
                        new OrderItem(Food.RED_WINE, 5)
                )));
        assertThat(order.calculateTotalPrice()).isEqualTo(new Payment(840_000));
    }


    @Test
    void 주문_메뉴와_수량을_가져올_수_있습니다() {
        final var order = new Order(LocalDate.of(2023, 12, 1),
                new OrderLine(List.of(
                        new OrderItem(Food.BBQ_RIBS, 10),
                        new OrderItem(Food.RED_WINE, 5)
                )));

        final var menus = order.selectMenuAndQuantity();
        assertAll(
                () -> assertThat(menus.get(Food.BBQ_RIBS.getName())).isEqualTo(10),
                () -> assertThat(menus.get(Food.RED_WINE.getName())).isEqualTo(5)
        );
    }


}