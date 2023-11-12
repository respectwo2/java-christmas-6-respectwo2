package event;

import domain.Customer;

public interface Event {
    String getEventName();
    int getDiscountAmount(Customer customer);

    void applyEvent(Customer customer);
}