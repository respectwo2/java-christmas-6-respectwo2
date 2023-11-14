package domain;

import java.util.List;

public class Customer {

	private int visitDate;
	private List<MenuItem> menuItems;
	private List<DiscountEvent> events;

	public Customer(int date, List<MenuItem> menus, List<DiscountEvent> events) {
		this.visitDate = date;
		this.menuItems = menus;
		this.events = events;
	}

	public Customer() {
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public int getVisitDate() {
		return visitDate;
	}

	public List<DiscountEvent> getEvents() {
		return events;
	}

	public int calculateTotalPrice() {
		int total = 0;
		for (MenuItem menuItem : menuItems) {
			total += MenuConst.valueOf(menuItem.getMenu()).getPrice() * menuItem.getQuantity();
		}
		return total;
	}

}
