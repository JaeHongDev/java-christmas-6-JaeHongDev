package christmas.view;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public final class InputView extends ConsoleWriter {

    private final ConsoleReader consoleReader;

    public InputView(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }


    public int readVisitOfDate() {
        this.print("""
                안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
                12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
                """);
        try {
            return Integer.parseInt(consoleReader.read());
        } catch (NumberFormatException exception) {
            throw new IllegalStateException("유효하지 않는 날짜입니다.");
        }
    }

    public Map<String, Integer> readOrderLine() {
        this.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        final var input = consoleReader.read();

        return Arrays.stream(input.split(","))
                .map(str -> str.split("-"))
                .peek(str -> {
                    if (str.length != 2) {
                        throw new IllegalArgumentException("유효하지 않는 입력");
                    }
                })
                .collect(toMap(splitStr -> splitStr[0],
                        splitStr -> Integer.parseInt(splitStr[1]),
                        (prev, next) -> {
                            throw new IllegalArgumentException("유효하지 않은 입력");
                        },
                        LinkedHashMap::new
                ));
    }
}
