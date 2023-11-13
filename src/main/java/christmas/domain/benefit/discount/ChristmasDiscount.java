package christmas.domain.benefit.discount;

import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class ChristmasDiscount implements Discount {

    private static final int BASE_PRICE = 1000;
    private static final int INCREASE_PRICE = 100;

    @Override
    public Payment apply(LocalDate date, OrderLine orderLine) {
        return new Payment(IntStream.iterate(BASE_PRICE, i -> i + INCREASE_PRICE)
                .limit(date.getDayOfMonth())
                .max()
                .orElse(0));
    }
}
