package christmas.event;

import christmas.domain.entity.Order;
import christmas.domain.vo.Badge;
import christmas.event.EventListener.ReturnEvent;
import christmas.repository.OrderRepository;
import christmas.state.DiscountResultState;

public record DiscountPaymentEvent(OrderRepository repository) implements ReturnEvent<DiscountResultState> {
    @Override
    public DiscountResultState execute() {
        final var dateOfVisit = repository.findDateOfVisit();
        final var orderLine = repository.findOrderLine();
        final var order = new Order(dateOfVisit, orderLine);
        final var benefitDetails = order.applyDiscount();

        return new DiscountResultState(
                dateOfVisit,
                order.selectMenuAndQuantity(),
                benefitDetails.totalPrice().value(),
                benefitDetails.getGiveawayMenu(),
                benefitDetails.getBenefitList(),
                benefitDetails.calculateBenefitAmount(),
                benefitDetails.calculateAmountAfterDiscount(),
                Badge.of(benefitDetails.calculateBenefitAmount()).getName()
        );

    }
}
