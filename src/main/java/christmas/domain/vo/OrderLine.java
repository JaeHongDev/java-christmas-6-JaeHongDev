package christmas.domain.vo;


import static christmas.domain.exception.DomainExceptionCode.INVALID_ORDER;

import java.util.List;
import java.util.Objects;

public record OrderLine(
        List<OrderItem> orderItems
) {

    public OrderLine {
        INVALID_ORDER.invokeByCondition(Objects.isNull(orderItems));
        INVALID_ORDER.invokeByCondition(orderItems.isEmpty());
        INVALID_ORDER.invokeByCondition(orderItems.stream().mapToInt(OrderItem::quantity).sum() > 20);
        INVALID_ORDER.invokeByCondition(orderItems.stream().map(OrderItem::food).allMatch(Menu.BEVERAGE::contains));
    }
}
