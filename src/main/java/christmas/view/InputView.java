package christmas.view;

import static christmas.domain.exception.DomainExceptionCode.INVALID_DATE;
import static christmas.domain.exception.DomainExceptionCode.INVALID_ORDER;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public final class InputView extends ConsoleWriter {

    private static final int MENU_FORMAT_SIZE = 2;

    private final ConsoleReader consoleReader;

    public InputView(ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    public int readVisitOfDate() {
        this.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return convertStringToInteger(consoleReader.read())
                .apply(INVALID_DATE.getMessage());
    }

    public Map<String, Integer> readOrderLine() {
        this.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        return Arrays.stream(consoleReader.read().split(","))
                .map(orderItem -> orderItem.split("-"))
                .peek(orderItem -> INVALID_ORDER.invokeByCondition(orderItem.length != MENU_FORMAT_SIZE))
                .collect(toMap(orderItem -> orderItem[0],
                        orderItem -> convertStringToInteger(orderItem[1]).apply(INVALID_ORDER.getMessage()),
                        (prev, next) -> {
                            throw INVALID_ORDER.createException(); //중복된 주문이 발생하는 경우
                        },
                        LinkedHashMap::new
                ));

    }

    private Function<String, Integer> convertStringToInteger(String input) {
        return (errorMessage) -> {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ignore) {
                throw new IllegalArgumentException(errorMessage);
            }
        };
    }
}
