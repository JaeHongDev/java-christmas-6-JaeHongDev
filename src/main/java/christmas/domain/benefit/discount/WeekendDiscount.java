package christmas.domain.benefit.discount;

import christmas.domain.vo.Menu;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;

public class WeekendDiscount implements Discount {
    @Override
    public Payment apply(LocalDate date, OrderLine orderLine) {
        return new Payment(orderLine.orderItems()
                .stream()
                .filter(orderItem -> Menu.MAIN_COURSE.contains(orderItem.food()))
                .mapToInt(OrderItem::quantity)
                .sum() * 2023);
    }
}
