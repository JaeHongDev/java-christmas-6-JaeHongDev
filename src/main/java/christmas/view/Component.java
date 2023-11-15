package christmas.view;

import java.util.function.Consumer;

public interface Component {
    void render();

    default void repeatWhenCauseError(Runnable runnable, Consumer<String> errorHandler) {
        try {
            runnable.run();
        } catch (IllegalArgumentException | IllegalStateException exception) {
            errorHandler.accept(exception.getMessage());
            repeatWhenCauseError(runnable, errorHandler);
        }
    }
}
