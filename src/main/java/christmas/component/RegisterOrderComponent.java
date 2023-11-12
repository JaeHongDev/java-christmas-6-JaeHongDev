package christmas.component;

import christmas.event.EventListener;
import christmas.view.Component;
import christmas.view.InputView;
import christmas.view.OutputView;

public record RegisterOrderComponent(InputView inputView, OutputView outputView, EventListener eventListener) implements Component {

    @Override
    public void render() {

        inputView.readOrderLine();

    }
}
