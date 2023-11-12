package christmas.component;

import christmas.event.EventListener;
import christmas.event.RegisterDateOfVisitEvent;
import christmas.view.Component;
import christmas.view.InputView;

public record RegisterDateOfVisitComponent(InputView inputView, EventListener eventListener) implements Component {
    @Override
    public void render() {
        eventListener.listenWithParameter(RegisterDateOfVisitEvent::new)
                .accept(inputView.readVisitOfDate());
    }
}
