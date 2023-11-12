package domain;

import java.util.List;

public class Customer {

	private int visitDate;
	private List<MenuItem> menus;

	public Customer(int date, List<MenuItem> menus) {
		this.visitDate = date;
		this.menus = menus;

	}

	public Customer() {
	}

	public List<MenuItem> getMenuItems() {
		return menus;
	}

	public int getVisitDate() {
		return visitDate;
	}

}
