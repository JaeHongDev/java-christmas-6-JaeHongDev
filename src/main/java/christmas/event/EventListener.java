package christmas.event;

import christmas.repository.OrderRepository;
import java.util.function.Consumer;
import java.util.function.Function;

public record EventListener(OrderRepository repository) {

    public void listen(Function<OrderRepository, Event> eventConstructor) {
        eventConstructor.apply(repository).execute();
    }

    public <T> Consumer<T> listenWithParameter(Function<OrderRepository, ParameterEvent<T>> eventConstructor) {
        return (input) -> eventConstructor.apply(repository).execute(input);
    }

    public <R> R listenWithResult(Function<OrderRepository, ReturnEvent<R>> eventConstructor) {
        return eventConstructor.apply(repository).execute();
    }

    public <T, R> Function<T, R> listenWithParameterAndResult(Function<OrderRepository, ParameterAndReturnEvent<T, R>> eventConstructor) {
        return (input) -> eventConstructor.apply(repository).execute(input);
    }

    public interface Event {
        void execute();
    }

    public interface ParameterEvent<T> {
        void execute(T input);
    }

    public interface ReturnEvent<R> {
        R execute();
    }

    public interface ParameterAndReturnEvent<T, R> {
        R execute(T input);
    }
}
