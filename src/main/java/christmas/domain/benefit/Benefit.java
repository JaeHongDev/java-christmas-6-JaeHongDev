package christmas.domain.benefit;


import christmas.domain.benefit.discount.ChristmasDiscount;
import christmas.domain.benefit.discount.Discount;
import christmas.domain.benefit.discount.SpecialDiscount;
import christmas.domain.benefit.discount.WeekDayDiscount;
import christmas.domain.benefit.discount.WeekendDiscount;
import christmas.domain.vo.OrderLine;
import christmas.domain.vo.Payment;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인 증정 이벤트: 할인 전
 * 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용
 */
public enum Benefit {

    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인", DiscountCondition.CHRISTMAS_DISCOUNT, new ChristmasDiscount()),
    WEEKDAY_DISCOUNT("평일 할인", DiscountCondition.WEEKDAY_DISCOUNT, new WeekDayDiscount()),
    WEEKEND_DISCOUNT("주말 할인", DiscountCondition.WEEKEND_DISCOUNT, new WeekendDiscount()),
    GIVEAWAY_BENEFIT("증정 이벤트", DiscountCondition.ALL_DAY, null),
    SPECIAL_DISCOUNT("특별 할인", DiscountCondition.SPECIAL_DISCOUNT, new SpecialDiscount()),
    ;
    private final String name;
    private final DiscountCondition discountCondition;
    private final Discount discount;

    Benefit(String name, DiscountCondition discountCondition, Discount discount) {
        this.name = name;
        this.discountCondition = discountCondition;
        this.discount = discount;
    }

    public boolean isSatisfyCondition(LocalDate date) {
        return discountCondition.isSatisfy(date);
    }

    public Payment applyDiscount(LocalDate date, OrderLine orderLine) {
        if (Objects.isNull(discount)) {
            throw new IllegalStateException("존재하지 않는 할인 혜택입니다.");
        }
        return discount.apply(date, orderLine);
    }

    public String getBenefitName() {
        return name;
    }

}
