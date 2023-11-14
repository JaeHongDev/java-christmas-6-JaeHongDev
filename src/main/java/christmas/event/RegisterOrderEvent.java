package christmas.event;

import christmas.domain.entity.Order;
import christmas.event.EventListener.ParameterEvent;
import christmas.repository.OrderRepository;
import christmas.state.CreateOrderState;

public record RegisterOrderEvent(OrderRepository orderRepository) implements ParameterEvent<CreateOrderState> {
    @Override
    public void execute(CreateOrderState createOrderState) {
        final var dateOfVisit = orderRepository.getVisitOfDate();
        orderRepository.save(new Order(dateOfVisit, createOrderState.toOrderLine()));
    }
}
