package christmas.fixture.order;

import christmas.domain.vo.Food;
import christmas.domain.vo.OrderItem;
import christmas.domain.vo.OrderLine;
import java.util.List;

public class OrderLineFixture {

    public static final OrderLine ONLY_MAIN_MENU = new OrderLine(List.of(
            new OrderItem(Food.BBQ_RIBS, 10),
            new OrderItem(Food.SEAFOOD_PASTA, 5)
    ));

}
