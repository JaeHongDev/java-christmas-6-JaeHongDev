package christmas.domain.benefit;

import christmas.domain.vo.Food;
import christmas.domain.vo.Payment;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

    public String getGiveawayMenu() {
        if (benefitPayments.containsKey(Benefit.GIVEAWAY_BENEFIT)) {
            return Food.CHAMPAGNE.getName() + " 1개";
        }
        return "없음";
    }

    public Map<String, Integer> getBenefitList() {
        return this.benefitPayments.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getBenefitName(),
                        (entry) -> entry.getValue().value(),
                        (prev, next) -> next,
                        LinkedHashMap::new
                ));

    }

    public int calculateBenefitAmount() {
        return benefitPayments.values()
                .stream()
                .mapToInt(Payment::value)
                .sum();
    }

    public int calculateAmountAfterDiscount() {
        final var amountAfterDiscount = benefitPayments.entrySet()
                .stream()
                .filter(benefitPaymentEntry -> !Benefit.GIVEAWAY_BENEFIT.equals(benefitPaymentEntry.getKey()))
                .map(Entry::getValue)
                .mapToInt(Payment::value)
                .sum();
        return totalPrice.minus(amountAfterDiscount);
    }
}
