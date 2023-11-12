package christmas.component;

import christmas.event.EventListener;
import christmas.event.RegisterOrderEvent;
import christmas.view.Component;
import christmas.view.InputView;
import christmas.view.OutputView;

public record RegisterOrderComponent(InputView inputView, OutputView outputView, EventListener eventListener) implements Component {

    @Override
    public void render() {
        eventListener.listenWithParameter(RegisterOrderEvent::new)
                .accept(inputView.readOrderLine());
    }
}
