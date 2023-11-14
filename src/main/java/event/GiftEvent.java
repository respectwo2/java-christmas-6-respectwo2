package event;

import domain.Customer;
import domain.DiscountEvent;

public class GiftEvent extends DiscountEvent {

    private static final String GIFT_EVENT_NAME = "증정 이벤트";
    private static final int GIFT_NEED_PRICE = 120000;
    private static final int GIFT_DISCOUNT_PRICE = 25000;

    public GiftEvent() {
        super(GIFT_EVENT_NAME, 0);
    }

    @Override
    public boolean isApplicable(Customer customer) {
        int price = customer.calculateTotalPrice();
        return price >= GIFT_NEED_PRICE;
    }

    @Override
    public int calculateDiscountAmount(Customer customer) {
        return GIFT_DISCOUNT_PRICE;
    }
}