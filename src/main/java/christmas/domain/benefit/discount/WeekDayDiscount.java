package christmas.domain.benefit.discount;

import christmas.domain.vo.Menu;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;

public class WeekDayDiscount implements Discount {
    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public Payment apply(LocalDate date, OrderLine orderLine) {
        return new Payment(orderLine.orderItems()
                .stream()
                .filter(orderItem -> Menu.DESSERT.contains(orderItem.food()))
                .mapToInt(OrderItem::quantity)
                .sum() * DISCOUNT_AMOUNT);
    }
}
