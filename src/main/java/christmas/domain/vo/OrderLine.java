package christmas.domain.vo;


import static christmas.domain.exception.DomainExceptionCode.INVALID_ORDER;

import java.util.List;
import java.util.Objects;

public record OrderLine(
        List<OrderItem> orderItems
) {
    private static final int MAX_ORDER_QUANTITY = 20;

    public OrderLine {
        INVALID_ORDER.invokeByCondition(Objects.isNull(orderItems));
        INVALID_ORDER.invokeByCondition(orderItems::isEmpty);
        INVALID_ORDER.invokeByCondition(isOrderQuantityExceeded(orderItems));
        INVALID_ORDER.invokeByCondition(hasOnlyBeverage(orderItems));
    }

    // compact 생성자가 동작을 완료하기 전 까지 메서드에서 orderItems를 참조할 수 없다.

    private static boolean isOrderQuantityExceeded(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::quantity)
                .sum() > MAX_ORDER_QUANTITY;
    }

    public static boolean hasOnlyBeverage(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::food)
                .allMatch(Menu.BEVERAGE::contains);
    }

}
