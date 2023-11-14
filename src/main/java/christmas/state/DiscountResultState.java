package christmas.state;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <주문 메뉴> 티본스테이크 1개 바비큐립 1개 초코케이크 2개 제로콜라 1개
 * <p>
 * <할인 전 총주문 금액> 142,000원
 * <p>
 * <증정 메뉴> 샴페인 1개
 * <p>
 * <혜택 내역> 크리스마스 디데이 할인: -1,200원 평일 할인: -4,046원 특별 할인: -1,000원 증정 이벤트: -25,000원
 * <p>
 * <총혜택 금액> -31,246원
 * <p>
 * <할인 후 예상 결제 금액> 135,754원
 * <p>
 * <12월 이벤트 배지>
 */
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
