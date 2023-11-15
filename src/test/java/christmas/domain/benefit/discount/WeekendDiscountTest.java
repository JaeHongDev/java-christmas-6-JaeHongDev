package christmas.domain.benefit.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.vo.Food;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import christmas.fixture.date.ChristmasLocalDateFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 주말할인 ")
class WeekendDiscountTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            1,2023
            10,20230
            20,40460
            """)
    void 주말_할인은_메인_메뉴_수량_1개당_2023원_할인됩니다(int quantity, int discountAmount) {
        final var orderLine = new OrderLine(List.of(new OrderItem(Food.BBQ_RIBS, quantity)));
        final var result = new WeekendDiscount().apply(ChristmasLocalDateFixture.CHRISTMAS_DATE, orderLine);

        assertThat(result).isEqualTo(new Payment(discountAmount));
    }

    @Test
    void 메인_메뉴가_없는_경우는_0원_할인_됩니다() {
        final var orderLine = new OrderLine(List.of(new OrderItem(Food.ICE_CREAM, 10)));
        final var result = new WeekendDiscount().apply(ChristmasLocalDateFixture.CHRISTMAS_DATE, orderLine);
        
        assertThat(result).isEqualTo(new Payment(0));

    }


}