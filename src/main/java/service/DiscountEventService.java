package service;

import domain.Customer;
import domain.DiscountEvent;

import java.util.List;

public class DiscountEventService {

	private static final int MIN_THIRD_BADGE = 5000;
	private static final int MIN_SECOND_BADGE = 10000;
	private static final int MIN_FIRST_BADGE = 20000;
	
    private final CustomerService customerService;

    public DiscountEventService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public int calculateTotalDiscount(Customer customer) {
        int totalDiscount = 0;
        List<DiscountEvent> events = customer.getEvents();

        for (DiscountEvent event : events) {
            event.calculateAndSetDiscountAmount(customer);
            totalDiscount += event.getDiscountAmount();
        }

        return totalDiscount;
    }

    public int calculateAfterDiscountTotalPrice(Customer customer) {
        int price = customerService.calculateTotalPrice(customer);
        int totalDiscount = calculateTotalDiscount(customer);
        return price - totalDiscount;
    }
    
    public String getBadge(Customer customer) {
        int totalDiscount = calculateTotalDiscount(customer);

        if (totalDiscount >= MIN_FIRST_BADGE) {
            return "산타";
        }
        if (totalDiscount >= MIN_SECOND_BADGE) {
            return "트리";
        }
        if (totalDiscount >= MIN_THIRD_BADGE) {
            return "별";
        }
        return null;
    }

}
