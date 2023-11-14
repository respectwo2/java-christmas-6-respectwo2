package domain;

import java.util.List;

public class Customer {

	private static final int MIN_THIRD_BADGE = 5000;
	private static final int MIN_SECOND_BADGE = 10000;
	private static final int MIN_FIRST_BADGE = 20000;

	private int visitDate;
	private List<MenuItem> menuItems;
	private List<DiscountEvent> events;

	public Customer(int date, List<MenuItem> menuItems, List<DiscountEvent> events) {
		this.visitDate = date;
		this.menuItems = menuItems;
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
			total += MenuItemsList.valueOf(menuItem.getMenu()).getPrice() * menuItem.getQuantity();
		}
		return total;
	}

	public String getBadge() {
		int totalDiscount = DiscountEvent.calculateTotalDiscount(this);

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
