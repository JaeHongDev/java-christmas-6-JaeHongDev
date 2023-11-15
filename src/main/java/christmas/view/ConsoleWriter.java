package christmas.view;

public sealed class ConsoleWriter permits InputView, OutputView {

    protected void println(final Object data) {
        System.out.println(data);
    }

    public void print(final Object data) {
        System.out.print(data);
    }

    protected void newLine() {
        System.out.println();
    }
}

