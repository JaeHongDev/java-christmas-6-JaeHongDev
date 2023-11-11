package christmas.domain.vo;

public record OrderItem(Food food, int quantity) {

    public OrderItem(String foodName, int quantity) {
        this(Food.of(foodName), quantity);
    }

}
