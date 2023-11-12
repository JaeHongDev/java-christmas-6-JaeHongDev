package christmas.domain.entity;

import static java.util.stream.Collectors.toMap;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitDetails;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

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
                .collect(toMap(OrderItem::getFoodName,
                        OrderItem::quantity,
                        (prev, next) -> next,
                        LinkedHashMap::new
                ));
    }

    public BenefitDetails applyDiscount() {
        return BenefitDetails.create(this.calculateTotalPrice())
                .merge(EnumSet.allOf(Benefit.class)
                        .stream()
                        .filter(benefit -> !Benefit.GIVEAWAY_BENEFIT.equals(benefit))
                        .filter(benefit -> benefit.isSatisfyCondition(localDate))
                        .collect(toMap(Function.identity(),
                                benefit -> benefit.applyDiscount(localDate, orderLine),
                                (prev, next) -> next,
                                LinkedHashMap::new)
                        ));
    }
}
