package christmas.state;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toUnmodifiableList;

import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import java.util.Map;

public record CreateOrderState(Map<String, Integer> value) {

    public OrderLine toOrderLine() {
        return value.entrySet()
                .stream()
                .map(entry -> new OrderItem(entry.getKey(), entry.getValue()))
                .collect(collectingAndThen(toUnmodifiableList(), OrderLine::new));
    }
}
