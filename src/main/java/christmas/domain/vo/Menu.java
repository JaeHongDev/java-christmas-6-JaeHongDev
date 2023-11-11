package christmas.domain.vo;

import static christmas.domain.vo.Food.BBQ_RIBS;
import static christmas.domain.vo.Food.CAESAR_SALAD;
import static christmas.domain.vo.Food.CHAMPAGNE;
import static christmas.domain.vo.Food.CHOCO_CAKE;
import static christmas.domain.vo.Food.CHRISTMAS_PASTA;
import static christmas.domain.vo.Food.ICE_CREAM;
import static christmas.domain.vo.Food.MUSHROOM_SOUP;
import static christmas.domain.vo.Food.RED_WINE;
import static christmas.domain.vo.Food.SEAFOOD_PASTA;
import static christmas.domain.vo.Food.TAPAS;
import static christmas.domain.vo.Food.T_BONE_STEAK;
import static christmas.domain.vo.Food.ZERO_COLA;

import java.util.List;

public enum Menu {
    APPETIZER(List.of(MUSHROOM_SOUP, TAPAS, CAESAR_SALAD)),
    MAIN_COURSE(List.of(T_BONE_STEAK, BBQ_RIBS, SEAFOOD_PASTA, CHRISTMAS_PASTA)),
    DESSERT(List.of(CHOCO_CAKE, ICE_CREAM)),
    BEVERAGE(List.of(ZERO_COLA, RED_WINE, CHAMPAGNE));
    private final List<Food> foods;

    Menu(List<Food> foods) {
        this.foods = foods;
    }

    public boolean contains(Food food) {
        return foods.contains(food);
    }
}
