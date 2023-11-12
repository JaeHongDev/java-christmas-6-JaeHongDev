package christmas.view;

import christmas.state.DiscountResultState;

public final class OutputView extends ConsoleWriter {
    public void printError(String message) {
        this.println(String.format("[ERROR] %s", message));
    }

    public void print(DiscountResultState state) {
        this.println("""
                        %s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
                                        
                        <주문 메뉴>
                        %s
                                        
                        <할인 전 총주문 금액>
                        %s
                                        
                        <증정 메뉴>
                        %s
                                        
                        <혜택 내역>
                        %s
                                        
                        <총혜택 금액>
                        %s
                                        
                        <할인 후 예상 결제 금액>
                        %s
                                        
                        <12월 이벤트 배지>
                        %s
                        """, state.dateOfVisit(),
                state.getOrderLine(),
                state.getAmountBeforeDiscount(),
                state.giveawayMenu(),
                state.getBenefitList(),
                state.getBenefitPrice(),
                state.getAmountAfterDiscount(),
                state.badge()
        );
    }
}
