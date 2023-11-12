package christmas.event;

import christmas.domain.entity.Order;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.event.EventListener.ParameterEvent;
import christmas.repository.OrderRepository;
import java.util.Map;
import java.util.stream.Collectors;

public record RegisterOrderEvent(OrderRepository orderRepository) implements ParameterEvent<Map<String, Integer>> {
    @Override
    public void execute(Map<String, Integer> menuAndQuantity) {
        final var orderLine = menuAndQuantity.entrySet()
                .stream()
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), OrderLine::new));

        final var dateOfVisit = orderRepository.getVisitOfDate();
        orderRepository.save(new Order(dateOfVisit, orderLine));
    }
}
