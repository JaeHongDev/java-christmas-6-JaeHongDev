package christmas.domain.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.domain.exception.DomainExceptionCode;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[도메인] 주문 목록")
class OrderLineTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreateTest {

        @Test
        void 최소_하나_이상의_주문을_가집니다() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new OrderLine(List.of()))
                    .withMessageContaining(DomainExceptionCode.INVALID_ORDER.getMessage());
        }

        @Test
        void 주문_목록은_비어있을_수_없습니다() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new OrderLine(null))
                    .withMessageContaining(DomainExceptionCode.INVALID_ORDER.getMessage());
        }

        @Test
        void 음료만_주문할_수_없습니다() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new OrderLine(List.of(
                            new OrderItem(Food.CHAMPAGNE, 5),
                            new OrderItem(Food.RED_WINE, 5))))
                    .withMessageContaining(DomainExceptionCode.INVALID_ORDER.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = {21, 22})
        void 최대_20개_까지만_주문할_수_있습니다(int size) {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new OrderLine(List.of(
                            new OrderItem(Food.BBQ_RIBS, size)))
                    ).withMessageContaining(DomainExceptionCode.INVALID_ORDER.getMessage());
        }
    }

}