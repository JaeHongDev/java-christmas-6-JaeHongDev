package christmas.domain.benefit;

import christmas.domain.vo.Food;
import christmas.domain.vo.Payment;
import java.util.LinkedHashMap;
import java.util.Map;

public record BenefitDetails(
        Payment totalPrice,
        Map<Benefit, Payment> benefitPayments
) {

    private static final int CRITERIA_FOR_BENEFIT = 1200000;

    public static BenefitDetails create(Payment totalPrice) {
        if (totalPrice.isGreaterThanEqual(CRITERIA_FOR_BENEFIT)) {
            return new BenefitDetails(totalPrice, new LinkedHashMap<>() {{
                this.put(Benefit.GIVEAWAY_BENEFIT, new Payment(Food.CHAMPAGNE.getPrice()));
            }});
        }
        return new BenefitDetails(totalPrice, new LinkedHashMap<>());
    }

    public BenefitDetails merge(final Map<Benefit, Payment> benefitPayments) {
        benefitPayments.putAll(this.benefitPayments);
        return new BenefitDetails(totalPrice, benefitPayments);
    }
}
