package christmas.event;

import christmas.event.EventListener.ParameterEvent;
import christmas.repository.OrderRepository;
import christmas.state.CreateOrderState;

public record RegisterOrderEvent(OrderRepository orderRepository) implements ParameterEvent<CreateOrderState> {
    @Override
    public void execute(CreateOrderState createOrderState) {
        final var dateOfVisit = orderRepository.findDateOfVisit();
        orderRepository.save(createOrderState.toOrderLine());
    }
}
