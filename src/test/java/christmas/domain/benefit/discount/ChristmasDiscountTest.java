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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 크리스마스 할인 테스트")
class ChristmasDiscountTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            1,1000
            2,1100
            24,3300
            25,3400
            """)
    void 크리스마스_할인은_1일부터_시작해서_25일까지_100원식_증가합니다(int day, int price) {
        final var payment = new ChristmasDiscount().apply(LocalDate.of(2023, 12, day), new OrderLine(List.of(
                new OrderItem(Food.BBQ_RIBS, 1)
        )));
        assertThat(payment).isEqualTo(new Payment(price));

    }

}