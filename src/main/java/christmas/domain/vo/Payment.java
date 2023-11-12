package christmas.domain.vo;

public record Payment(int value) {
    public boolean isGreaterThanEqual(int other) {
        return value >= other;
    }
}
