package christmas.event;

import christmas.event.EventListener.ParameterEvent;
import christmas.repository.OrderRepository;
import java.time.DateTimeException;
import java.time.LocalDate;

public record RegisterDateOfVisitEvent(OrderRepository orderRepository) implements ParameterEvent<Integer> {
    @Override
    public void execute(Integer input) {
        orderRepository.save(parse(input));
    }

    private LocalDate parse(int day) {
        try {
            return LocalDate.of(2023, 12, day);
        } catch (DateTimeException exception) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
