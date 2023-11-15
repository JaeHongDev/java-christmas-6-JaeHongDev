package christmas.view;

import christmas.component.RegisterReservationComponent;
import christmas.event.EventListener;
import java.util.List;

public class MainComponents {

    private final List<Component> components;

    public MainComponents(InputView inputView, OutputView outputView, EventListener eventListener) {
        this.components = List.of(new RegisterReservationComponent(inputView, outputView, eventListener));
    }

    public void renderAll() {
        components.forEach(Component::render);
    }
}
