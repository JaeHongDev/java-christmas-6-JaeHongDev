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

    public static BenefitDetails create(Payment totalPrice) {
        return new BenefitDetails(totalPrice, new LinkedHashMap<>());
    }

    public BenefitDetails merge(final Map<Benefit, Payment> benefitPayments) {
        final var filteredZeroPayments = benefitPayments.entrySet()
                .stream()
                .filter(benefitPaymentEntry -> !benefitPaymentEntry.getValue().isZero())
                .collect(Collectors.toMap(Entry::getKey,
                        Entry::getValue,
                        (prev, next) -> next,
                        LinkedHashMap::new)
                );

        filteredZeroPayments.putAll(this.benefitPayments);

        return new BenefitDetails(totalPrice, filteredZeroPayments);
    }

    public String getGiveawayMenu() {
        if (benefitPayments.containsKey(Benefit.GIVEAWAY_BENEFIT)) {
            return String.format("%s 1개", Food.CHAMPAGNE.getName());
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

    public BenefitDetails merge(Benefit benefit, Payment payment) {
        benefitPayments.put(benefit, payment);
        return new BenefitDetails(totalPrice, benefitPayments);
    }

}
