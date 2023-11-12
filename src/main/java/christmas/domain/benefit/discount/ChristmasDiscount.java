package christmas.domain.benefit.discount;

import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class ChristmasDiscount implements Discount {
    @Override
    public Payment apply(LocalDate date, OrderLine orderLine) {
        return new Payment(IntStream.iterate(1000, i -> i + 100)
                .limit(date.getDayOfMonth())
                .max()
                .orElse(0));
    }
}
