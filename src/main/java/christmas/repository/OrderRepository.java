package christmas.repository;

import christmas.domain.vo.OrderLine;
import java.time.LocalDate;

public class OrderRepository {

    private LocalDate dateOfVisit;
    private OrderLine orderLine;

    public void save(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public LocalDate findDateOfVisit() {
        return dateOfVisit;
    }

    public void save(OrderLine order) {
        this.orderLine = order;
    }

    public OrderLine findOrderLine() {
        return orderLine;
    }

}
