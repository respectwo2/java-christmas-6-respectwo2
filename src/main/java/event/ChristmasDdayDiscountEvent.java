package event;

import domain.Customer;
import domain.Events;

public class ChristmasDdayDiscountEvent implements Event {

	private static final String CHRISTMAS_DISCOUNT_EVENT = "크리스마스 디데이 할인";
    private Events events;

    public ChristmasDdayDiscountEvent(Events events) {
        this.events = events;
    }
	
	public void applyEvent(Customer customer) {
		int date = customer.getVisitDate();
		if(date >= 1 && date<=25) {
			events.addEvent(this);
		}
	}

	@Override
	public String getEventName() {
		return CHRISTMAS_DISCOUNT_EVENT;
	}


	public int getDiscountAmount(Customer customer) {
		int visitDate = customer.getVisitDate();
		if (visitDate >= 1 && visitDate <= 25) {
			return 1000 + (visitDate - 1) * 100;
		}
		return 0;
	}

}
