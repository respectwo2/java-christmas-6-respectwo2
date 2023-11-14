package event;

import domain.Customer;
import domain.DiscountEvent;

public class ChristmasDdayDiscountEvent extends DiscountEvent {

	private static final String EVENT_NAME = "크리스마스 디데이 할인";
	private static final int EVENT_START = 1;
	private static final int EVENT_END = 25;
	private static final int CHRISTMAS_DDAY_DISCOUNT_STARTPRICE = 1000;

	public ChristmasDdayDiscountEvent() {
		super(EVENT_NAME, 0);
	}

	@Override
	public boolean isApplicable(Customer customer) {
		int visitDate = customer.getVisitDate();
		return visitDate >= 1 && visitDate <= 25;
	}

	@Override
	public int calculateDiscountAmount(Customer customer) {
		int visitDate = customer.getVisitDate();
		if (visitDate >= EVENT_START && visitDate <= EVENT_END) {
			return CHRISTMAS_DDAY_DISCOUNT_STARTPRICE + (visitDate - 1) * 100;
		}
		return 0;
	}
}