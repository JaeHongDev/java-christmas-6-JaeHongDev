package christmas.domain.vo;

import christmas.domain.exception.DomainExceptionCode;

public record OrderItem(Food food, int quantity) {

    public OrderItem {
        if (quantity <= 0) {
            throw DomainExceptionCode.INVALID_ORDER.createException();
        }
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
