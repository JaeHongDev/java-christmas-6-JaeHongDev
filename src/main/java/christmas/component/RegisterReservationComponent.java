package christmas.component;

import christmas.event.DiscountPaymentEvent;
import christmas.event.EventListener;
import christmas.event.RegisterDateOfVisitEvent;
import christmas.event.RegisterOrderEvent;
import christmas.state.CreateOrderState;
import christmas.view.Component;
import christmas.view.InputView;
import christmas.view.OutputView;

public record RegisterReservationComponent(InputView inputView, OutputView outputView, EventListener eventListener) implements Component {

    @Override
    public void render() {
        repeatWhenCauseError(this::registerDateOfVisit, outputView::printError);
        repeatWhenCauseError(this::registerOrderLine, outputView::printError);

        final var discountResultState = eventListener.listenWithResult(DiscountPaymentEvent::new);
        outputView.print(discountResultState);
    }

    private void registerDateOfVisit() {
        eventListener.listenWithParameter(RegisterDateOfVisitEvent::new)
                .accept(inputView.readVisitOfDate());
    }

    private void registerOrderLine() {
        eventListener.listenWithParameter(RegisterOrderEvent::new)
                .accept(new CreateOrderState(inputView.readOrderLine()));
    }
    
}
