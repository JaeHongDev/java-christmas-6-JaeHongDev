package christmas.view;

public final class OutputView extends ConsoleWriter {
    public void printError(String message) {
        this.println(String.format("[ERROR] %s", message));
    }
}
