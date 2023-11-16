package christmas.state;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public record DiscountResultState(
        LocalDate dateOfVisit,
        Map<String, Integer> orderItem,
        int amountBeforeDiscount,
        String giveawayMenu,
        Map<String, Integer> benefitList,
        int benefitPrice,
        int amountAfterDiscount,
        String badge
) {

    public String getDateOfVisit() {
        return dateOfVisit.format(DateTimeFormatter.ofPattern("MM월 d일"));
    }

    public String getOrderLine() {
        return orderItem.entrySet().stream()
                .map(entry -> String.format("%s %d개", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }

    public String getAmountBeforeDiscount() {
        return String.format("%,d원", amountBeforeDiscount);
    }

    public String getGiveawayMenu() {
        return String.format("%s", giveawayMenu);
    }

    public String getBenefitList() {
        if (benefitList.isEmpty()) {
            return "없음";
        }
        return benefitList.entrySet().stream()
                .map(entry -> String.format("%s : %,d원", entry.getKey(), entry.getValue() * -1))
                .collect(Collectors.joining("\n"));
    }

    public String getBenefitPrice() {
        return String.format("%,d원", benefitPrice * -1);
    }

    public String getAmountAfterDiscount() {
        return String.format("%,d원", amountAfterDiscount);
    }

}
