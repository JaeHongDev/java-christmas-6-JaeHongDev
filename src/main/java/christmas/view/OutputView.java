package christmas.view;

import christmas.state.DiscountResultState;

public final class OutputView extends ConsoleWriter {
    private static final String SPLITERATOR = System.lineSeparator();

    public void printError(String message) {
        this.println(String.format("[ERROR] %s 다시 입력해 주세요.", message));
    }

    public void print(DiscountResultState state) {
        this.print(String.join(SPLITERATOR + SPLITERATOR,
                String.format("%s에 우테코 식닥에서 받을 이벤트 혜택 미리보기", state.getDateOfVisit()),
                String.join(SPLITERATOR, "<주문 메뉴>", state.getOrderLine()),
                String.join(SPLITERATOR, "<할인 전 총주문 금액>", state.getAmountBeforeDiscount()),
                String.join(SPLITERATOR, "<증정 메뉴>", state.giveawayMenu()),
                String.join(SPLITERATOR, "<혜택 내역>", state.getBenefitList()),
                String.join(SPLITERATOR, "<총혜택 금액>", state.getBenefitPrice()),
                String.join(SPLITERATOR, "<할인 후 예상 결제 금액>", state.getAmountAfterDiscount()),
                String.join(SPLITERATOR, "<12월 이벤트 배지>", state.badge())
        ));
    }
}
