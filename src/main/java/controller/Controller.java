package controller;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import domain.DiscountEvent;
import domain.DiscountWeekEvent;
import domain.MenuItem;
import view.InputView;
import view.OutputView;
import event.ChristmasDdayDiscountEvent;
import event.GiftEvent;
import event.WeekdayDiscountEvent;
import event.WeekendDiscountEvent;
public class Controller {

	public static void play() {
		Customer customer = generateCustomer();
		OutputView.printNotice(customer);
		OutputView.printMenu(customer);
		OutputView.printBeforeDiscountPrice(customer);
		OutputView.printGiftMenu(customer);
        OutputView.printEvents(customer.getEvents());
        OutputView.printTotalEventPrice(customer);
        OutputView.printAfterDiscountPrice(customer);

	}


	private static Customer generateCustomer() {
		
        InputView input = new InputView();
        int visitDate = input.readDate();
        List<MenuItem> menus = input.readMenu();
        Customer customer = new Customer(visitDate, menus, new ArrayList<>());
        eventApply(customer);
        
        return customer;
    }
	
    public static void eventApply(Customer customer) {
        List<DiscountEvent> events = customer.getEvents();

        DiscountEvent christmasEvent = new ChristmasDdayDiscountEvent();
        DiscountEvent giftEvent = new GiftEvent();
        DiscountEvent weekdayEvent = new WeekdayDiscountEvent();
        DiscountEvent weekendEvent = new WeekendDiscountEvent();

        applyEventIfApplicable(christmasEvent, customer, events);
        applyEventIfApplicable(giftEvent, customer, events);
        applyEventIfApplicable(weekdayEvent, customer, events);
        applyEventIfApplicable(weekendEvent, customer, events);
    }

    private static void applyEventIfApplicable(DiscountEvent event, Customer customer, List<DiscountEvent> events) {
        if (event.isApplicable(customer)) {
        	event.calculateAndSetDiscountAmount(customer);
            events.add(event);
        }
    }

}
