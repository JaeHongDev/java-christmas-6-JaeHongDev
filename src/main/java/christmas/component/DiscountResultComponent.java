package christmas.component;

import christmas.event.DiscountPaymentEvent;
import christmas.event.EventListener;
import christmas.view.Component;
import christmas.view.OutputView;

public record DiscountResultComponent(OutputView outputView, EventListener eventListener) implements Component {
    @Override
    public void render() {

        final var discountResultState = eventListener.listenWithResult(DiscountPaymentEvent::new);

        outputView.print(discountResultState);
    }
}
