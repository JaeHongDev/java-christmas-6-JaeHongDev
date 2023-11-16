package christmas.view;

import christmas.event.EventListener;
import christmas.repository.OrderRepository;
import christmas.view.ConsoleReader.ConsoleReaderImpl;

public class MainView {

    public void open() {
        final var mainComponents = new MainComponents(
                new InputView(new ConsoleReaderImpl()),
                new OutputView(),
                new EventListener(new OrderRepository())
        );
        mainComponents.renderAll();
    }
}
