package event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import domain.Customer;
import domain.DiscountEvent;
import domain.DiscountWeekEvent;
import domain.MenuItemsList;
import domain.MenuItem;

public class WeekdayDiscountEvent extends DiscountEvent {

	private static final String WEEKDAY_DISCOUNT_NAME = "평일 할인";

	public WeekdayDiscountEvent() {
		super(WEEKDAY_DISCOUNT_NAME, 0);
	}

	@Override
	public boolean isApplicable(Customer customer) {
		LocalDate localDate = LocalDate.of(2023, 12, customer.getVisitDate());
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		DiscountWeekEvent discountEvent = DiscountWeekEvent.getDiscountEvent(customer);

		for (MenuItem menuItem : customer.getMenuItems()) {
			String menuType = MenuItemsList.valueOf(menuItem.getMenu()).getType();
			if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY
					&& menuType.equals(discountEvent.getMenuType())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int calculateDiscountAmount(Customer customer) {
		int discountAmount = 0;
		DiscountWeekEvent discountEvent = DiscountWeekEvent.getDiscountEvent(customer);

		for (MenuItem menuItem : customer.getMenuItems()) {
			String menuType = MenuItemsList.valueOf(menuItem.getMenu()).getType();
			if (menuType.equals(discountEvent.getMenuType())) {
				discountAmount += menuItem.getQuantity() * discountEvent.getDiscountAmount();
			}
		}
		return discountAmount;
	}
}