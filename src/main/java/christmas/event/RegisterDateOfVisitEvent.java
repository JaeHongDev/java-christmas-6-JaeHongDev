package christmas.event;

import christmas.domain.exception.DomainExceptionCode;
import christmas.domain.util.ChristmasLocalDate;
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
            return ChristmasLocalDate.create(day);
        } catch (DateTimeException exception) {
            throw DomainExceptionCode.INVALID_DATE.createException();
        }
    }
}
