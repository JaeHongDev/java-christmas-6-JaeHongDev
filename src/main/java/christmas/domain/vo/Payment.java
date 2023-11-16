package christmas.domain.vo;

public record Payment(int value) {

    public boolean isGreaterThanEqual(int other) {
        return value >= other;
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isLessThan(int other) {
        return value < other;
    }

    public int minus(int other) {
        return value - other;
    }

}
