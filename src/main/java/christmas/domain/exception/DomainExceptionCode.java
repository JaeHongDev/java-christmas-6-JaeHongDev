package christmas.domain.exception;

import java.util.function.Supplier;

public enum DomainExceptionCode {

    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_ORDER("유효하지 않은 주문입니다.");

    private final String message;

    DomainExceptionCode(String message) {
        this.message = message;
    }

    public DomainException createException() {
        return new DomainException(message);
    }

    public void invokeByCondition(boolean condition) {
        if (condition) {
            throw new DomainException(message);
        }
    }

    public void invokeByCondition(Supplier<Boolean> condition) {
        if (condition.get()) {
            throw new DomainException(message);
        }
    }

    public String getMessage() {
        return message;
    }

}
