package event;

import domain.Customer;
import domain.DiscountEvent;

public class ChristmasDdayDiscountEvent extends DiscountEvent {

    private static final String EVENT_NAME = "크리스마스 디데이 할인";

    public ChristmasDdayDiscountEvent() {
        super(EVENT_NAME, 0);
    }

    @Override
    public boolean isApplicable(Customer customer) {
        int visitDate = customer.getVisitDate();
        return visitDate >= 1 && visitDate <= 25;
    }

    @Override
    public int calculateDiscountAmount(Customer customer) {
        int visitDate = customer.getVisitDate();
        if (visitDate >= 1 && visitDate <= 25) {
            return 1000 + (visitDate - 1) * 100;
        }
        return 0;
    }
}