package service;

import java.util.List;

import domain.Customer;
import domain.DiscountEvent;
import event.ChristmasDdayDiscountEvent;
import event.GiftEvent;
import event.WeekdayDiscountEvent;
import event.WeekendDiscountEvent;

public class EventApplyService {
	
	
    private static final int MIN_EVENT_PRICE = 10000;
    private final CustomerService customerService;

    public EventApplyService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void eventApply(Customer customer) {
        List<DiscountEvent> events = customer.getEvents();

        DiscountEvent christmasEvent = new ChristmasDdayDiscountEvent();
        DiscountEvent giftEvent = new GiftEvent(customerService);
        DiscountEvent weekdayEvent = new WeekdayDiscountEvent();
        DiscountEvent weekendEvent = new WeekendDiscountEvent();

        applyEventIfApplicable(christmasEvent, customer, events);
        applyEventIfApplicable(giftEvent, customer, events);
        applyEventIfApplicable(weekdayEvent, customer, events);
        applyEventIfApplicable(weekendEvent, customer, events);
    }

    private void applyEventIfApplicable(DiscountEvent event, Customer customer, List<DiscountEvent> events) {
        int totalPrice = customerService.calculateTotalPrice(customer);

        if (event.isApplicable(customer) && totalPrice > MIN_EVENT_PRICE) {
            event.calculateAndSetDiscountAmount(customer);
            events.add(event);
        }
    }}
