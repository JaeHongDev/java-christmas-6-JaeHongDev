package christmas.repository;

import java.time.LocalDate;

public class OrderRepository {
    private LocalDate visitOfDate;

    public void save(LocalDate dateOfVisit) {
        this.visitOfDate = dateOfVisit;
    }
}
