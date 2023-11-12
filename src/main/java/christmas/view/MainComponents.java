package christmas.view;

import christmas.component.RegisterDateOfVisitComponent;
import christmas.component.RegisterOrderComponent;
import christmas.event.EventListener;
import christmas.view.Component.ComponentRenderResult;
import java.util.List;
import java.util.stream.Stream;

public class MainComponents {

    private final List<Component> components;
    private final OutputView outputView;

    public MainComponents(InputView inputView, OutputView outputView, EventListener eventListener) {
        this.components = List.of(
                new RegisterDateOfVisitComponent(inputView, eventListener),
                new RegisterOrderComponent(inputView, outputView, eventListener)
        );
        this.outputView = outputView;
    }

    public void renderAll() {
        components.forEach(component -> Stream.generate(component::invoke)
                .takeWhile(ComponentRenderResult::isContinue)
                .forEach(componentRenderResult -> outputView.printError(componentRenderResult.errorMessage()))
        );
    }
}
