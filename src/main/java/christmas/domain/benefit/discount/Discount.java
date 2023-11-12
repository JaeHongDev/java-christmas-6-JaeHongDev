package christmas.domain.benefit.discount;

import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;

public interface Discount {
    Payment apply(LocalDate date, OrderLine orderLine);
}
