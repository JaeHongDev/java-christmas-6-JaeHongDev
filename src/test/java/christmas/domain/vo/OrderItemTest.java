package christmas.domain.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.exception.DomainExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 주문 항목 테스트")
class OrderItemTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreateTest {

        @Test
        void 존재하지_않는_음식이름으로_주문항목을_만들_수_없습니다() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new OrderItem("없음", 1))
                    .withMessageContaining(DomainExceptionCode.INVALID_ORDER.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -2})
        void 주문_수량이_1개보다_작을_수_없습니다(int input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new OrderItem(Food.BBQ_RIBS, input))
                    .withMessageContaining(DomainExceptionCode.INVALID_ORDER.getMessage());
        }
    }

}