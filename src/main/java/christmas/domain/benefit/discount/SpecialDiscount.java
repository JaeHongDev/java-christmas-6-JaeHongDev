package christmas.domain.benefit.discount;

import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;

public class SpecialDiscount implements Discount {

    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public Payment apply(LocalDate date, OrderLine orderLine) {
        return new Payment(DISCOUNT_AMOUNT);
    }

}
