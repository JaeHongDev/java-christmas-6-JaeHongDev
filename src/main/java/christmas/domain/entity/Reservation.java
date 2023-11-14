package christmas.domain.entity;

import static java.util.stream.Collectors.toMap;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitDetails;
import christmas.domain.vo.Food;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Reservation {
    private final LocalDate localDate;
    private final OrderLine orderLine;

    public Reservation(LocalDate localDate, OrderLine orderLine) {
        this.localDate = localDate;
        this.orderLine = orderLine;
    }

    public Payment calculateTotalPrice() {
        return new Payment(orderLine.orderItems()
                .stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum()
        );
    }

    public Map<String, Integer> selectMenuAndQuantity() {
        return orderLine.orderItems()
                .stream()
                .collect(toMap(OrderItem::getFoodName,
                        OrderItem::quantity,
                        (prev, next) -> next,
                        LinkedHashMap::new)
                );
    }

    public BenefitDetails applyDiscount() {
        final var totalPrice = this.calculateTotalPrice();
        if (totalPrice.isLessThan(10_000)) {
            return BenefitDetails.create(totalPrice);
        }
        return applyGiveawayDiscount(totalPrice).merge(EnumSet.allOf(Benefit.class)
                .stream()
                .filter(benefit -> !Benefit.GIVEAWAY_BENEFIT.equals(benefit))
                .filter(benefit -> benefit.isSatisfyCondition(localDate))
                .collect(toMap(Function.identity(),
                        benefit -> benefit.applyDiscount(localDate, orderLine),
                        (prev, next) -> next,
                        LinkedHashMap::new)
                ));
    }

    private BenefitDetails applyGiveawayDiscount(Payment totalPrice) {
        if (totalPrice.isGreaterThanEqual(120_000)) {
            return BenefitDetails.create(totalPrice)
                    .merge(Benefit.GIVEAWAY_BENEFIT, new Payment(Food.CHAMPAGNE.getPrice()));
        }
        return BenefitDetails.create(totalPrice);
    }
}
