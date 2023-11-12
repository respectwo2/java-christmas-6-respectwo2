package controller;

import java.util.List;

import domain.Customer;
import domain.MenuItem;
import view.InputView;
import view.OutputView;

public class Controller {

	public static void play() {
		Customer customer = new Customer();
		generateCustomer(customer);
	}
	
	private static Customer generateCustomer(Customer inputCustomoer) {
		InputView input = new InputView();
		int visitDate = input.readDate();
		List<MenuItem> menus = input.readMenu();
		Customer inputCustomer = new Customer(visitDate,menus);
		OutputView.printMenu(inputCustomer);
		return inputCustomer;
		
	}
}
