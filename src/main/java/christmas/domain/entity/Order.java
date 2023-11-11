package christmas.domain.entity;

import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private final LocalDate localDate;
    private final OrderLine orderLine;

    public Order(LocalDate localDate, OrderLine orderLine) {
        this.localDate = localDate;
        this.orderLine = orderLine;
    }

    public Payment calculateTotalPrice() {
        return new Payment(orderLine.orderItems()
                .stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum());
    }

    public Map<String, Integer> selectMenuAndQuantity() {
        return orderLine.orderItems()
                .stream()
                .collect(Collectors.toMap(OrderItem::getFoodName,
                        OrderItem::quantity,
                        (prev, next) -> next,
                        LinkedHashMap::new
                ));
    }
}
