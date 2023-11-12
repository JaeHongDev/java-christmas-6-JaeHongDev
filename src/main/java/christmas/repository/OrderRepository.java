package christmas.repository;

import christmas.domain.entity.Order;
import java.time.LocalDate;

public class OrderRepository {
    private LocalDate visitOfDate;
    private Order order;

    public void save(LocalDate dateOfVisit) {
        this.visitOfDate = dateOfVisit;
    }

    public LocalDate getVisitOfDate() {
        return visitOfDate;
    }

    public void save(Order order) {
        this.order = order;
    }
}
