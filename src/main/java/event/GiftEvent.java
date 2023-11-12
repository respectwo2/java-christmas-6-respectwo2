package event;

import domain.Customer;
import domain.Events;

public class GiftEvent implements Event {

    private static final int GIFT_NEED_PRICE = 120000;
    private static final int GIFT_DISCOUNT_PRICE = 25000;
	private static final String GIFT_EVENT_NAME = "증정 이벤트";
	
	private Events events;
	
    public GiftEvent(Events events) {
        this.events = events;
    }
	

    @Override
    public void applyEvent(Customer customer) {
        int price = customer.calculateTotalPrice();
        if (price >= GIFT_NEED_PRICE) {
            events.addEvent(this); 
        }
    }

    @Override
    public String getEventName() {
        return GIFT_EVENT_NAME;
    }

	@Override
	public int getDiscountAmount(Customer customer) {
		return GIFT_DISCOUNT_PRICE;
	}
}
