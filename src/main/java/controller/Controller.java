package controller;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import domain.Events;
import domain.MenuItem;
import view.InputView;
import view.OutputView;
import event.ChristmasDdayDiscountEvent;
import event.Event;
public class Controller {

	public static void play() {
		Customer customer = generateCustomer();
		OutputView.printMenu(customer);
		OutputView.printBeforeDiscountPrice(customer);
		checkEvents(customer);
		
	}

	private static Customer generateCustomer() {
        InputView input = new InputView();
        int visitDate = input.readDate();
        List<MenuItem> menus = input.readMenu();
        return new Customer(visitDate, menus, new Events());
    }
	
	private static void checkEvents(Customer customer) {
		List<MenuItem> menus = customer.getMenuItems();
		int date = customer.getVisitDate();
		
	}
	
}
