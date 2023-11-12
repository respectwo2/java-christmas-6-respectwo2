package controller;

import java.util.List;

import domain.Customer;
import domain.MenuItem;
import view.InputView;

public class Controller {

	public static void play() {
		generateCustomer();
	}
	
	private static Customer generateCustomer() {
		InputView input = new InputView();
		int visitDate = input.readDate();
		List<MenuItem> menus = input.readMenu();
		Customer customer = new Customer(visitDate,menus);
		return customer;
		
	}
}
