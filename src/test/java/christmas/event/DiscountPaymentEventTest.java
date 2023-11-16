package christmas.event;

import christmas.domain.util.ChristmasLocalDate;
import christmas.fixture.order.OrderLineFixture;
import christmas.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("[통합 테스트]")
class DiscountPaymentEventTest {

    /**
     * 테스트 경우의 수
     * <br> 1. 주문 금액이 1만원을 넘기지 못해서 할인 혜택을 적용받지 못하는 경우
     * <br> 2. 크리스마스 할인이 적용되는데 1만원이 넘지 못한 경우
     * <br> 3. 평일 할인이 적용되는데 1만원이 넘지 못한 경우
     * <br> 4. 주말 할인이 적용되는데 1만원이 넘지 못한 경우
     *
     * <br> 1. 크리스마스 할인이 적용되는 경우
     * <br> 2. 크리스마스 할인에 증정할인이 적용되는 경우
     * <br> 3. 크리스마스 할인에
     **/

    @Test
    void 주문_금액이_1만원을_넘기지_못해서_할인_혜택을_적용받지_못하는_경우() {
        final var orderRepository = new OrderRepository();
        final var discountPaymentEvent = new DiscountPaymentEvent(orderRepository);

        orderRepository.save(ChristmasLocalDate.create(25));
        orderRepository.save(OrderLineFixture.ONLY_MAIN_MENU);

        discountPaymentEvent.execute();

    }

}