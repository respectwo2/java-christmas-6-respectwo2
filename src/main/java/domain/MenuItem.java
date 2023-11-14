package domain;

public class MenuItem {

	private String menu;
	private int quantity;

	public MenuItem(String menu, int quantity) {

		this.menu = menu;
		this.quantity = quantity;

	}

	public String getMenu() {
		return menu;
	}

	public int getQuantity() {
		return quantity;
	}

}
