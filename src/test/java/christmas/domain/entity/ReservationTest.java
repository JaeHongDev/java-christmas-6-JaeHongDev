package christmas.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.domain.util.ChristmasLocalDate;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 주문 테스트")
class ReservationTest {

    @Test
    void 할인_전_금액을_가지고_옵니다() {
        final var order = new Reservation(LocalDate.of(2023, 12, 1),
                new OrderLine(List.of(
                        new OrderItem(Food.BBQ_RIBS, 10),
                        new OrderItem(Food.RED_WINE, 5)
                )));
        assertThat(order.calculateTotalPrice()).isEqualTo(new Payment(840_000));
    }


    @Test
    void 주문_메뉴와_수량을_가져올_수_있습니다() {
        final var order = new Reservation(LocalDate.of(2023, 12, 1),
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

    @Test
    void _1만원_보다_작은_경우는_할인_적용이_불가능합니다() {
        final var order = new Reservation(ChristmasLocalDate.create(25), new OrderLine(
                List.of(
                        new OrderItem(Food.ICE_CREAM, 1)
                )
        ));
        assertAll(
                () -> assertThat(order.applyDiscount().totalPrice()).isEqualTo(new Payment(5000)),
                () -> assertThat(order.applyDiscount().calculateAmountAfterDiscount()).isEqualTo(5000)
        );
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            2,10000,5954,
            3,15000,8931
            """)
    void _1만원_이상의_경우_할인이_적용됩니다(int quantity, int payment, int discountAmount) {
        final var order = new Reservation(ChristmasLocalDate.create(28), new OrderLine(
                List.of(new OrderItem(Food.ICE_CREAM, quantity))
        ));
        final var benefitDetails = order.applyDiscount();
        assertAll(
                () -> assertThat(benefitDetails.totalPrice()).isEqualTo(new Payment(payment)),
                () -> assertThat(benefitDetails.calculateAmountAfterDiscount()).isEqualTo(discountAmount)
        );

    }


}