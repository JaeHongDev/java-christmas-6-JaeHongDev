package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public interface ConsoleReader {

    String read();

    class ConsoleReaderImpl implements ConsoleReader {

        @Override
        public String read() {
            return Console.readLine();
        }
    }
}
