package christmas.event;

import christmas.repository.OrderRepository;
import java.util.function.Consumer;
import java.util.function.Function;

public record EventListener(OrderRepository repository) {

    public <T> Consumer<T> listenWithParameter(Function<OrderRepository, ParameterEvent<T>> eventConstructor) {
        return (input) -> eventConstructor.apply(repository).execute(input);
    }

    public <R> R listenWithResult(Function<OrderRepository, ReturnEvent<R>> eventConstructor) {
        return eventConstructor.apply(repository).execute();
    }

    public interface ParameterEvent<T> {
        void execute(T input);
    }

    public interface ReturnEvent<R> {
        R execute();
    }
    
}
