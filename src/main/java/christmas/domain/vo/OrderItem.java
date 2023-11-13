package christmas.domain.vo;

import static christmas.domain.exception.DomainExceptionCode.INVALID_ORDER;

public record OrderItem(Food food, int quantity) {

    public OrderItem {
        INVALID_ORDER.invokeByCondition(quantity <= 0);
    }

    public OrderItem(String foodName, int quantity) {
        this(Food.of(foodName), quantity);
    }

    public String getFoodName() {
        return food.getName();
    }

    public int getTotalPrice() {
        return food.calculatePrice(quantity);
    }
}
