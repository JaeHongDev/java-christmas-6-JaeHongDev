package christmas.view;

import static java.util.stream.Collectors.toMap;

import christmas.domain.exception.DomainExceptionCode;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public final class InputView extends ConsoleWriter {

    private final ConsoleReader consoleReader;
    private final int MENU_FORMAT_SIZE = 2;

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
            throw DomainExceptionCode.INVALID_DATE.createException();
        }
    }

    public Map<String, Integer> readOrderLine() {
        this.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        return Arrays.stream(consoleReader.read().split(","))
                .map(str -> str.split("-"))
                .peek(str -> DomainExceptionCode.INVALID_ORDER.invokeByCondition(str.length != MENU_FORMAT_SIZE))
                .collect(toMap(splitStr -> splitStr[0],
                        splitStr -> convertStringToInteger(splitStr[1]),
                        (prev, next) -> {
                            //중복된 주문이 발생하는 경우
                            throw DomainExceptionCode.INVALID_ORDER.createException();
                        },
                        LinkedHashMap::new
                ));
    }

    private int convertStringToInteger(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw DomainExceptionCode.INVALID_ORDER.createException();
        }
    }

}
