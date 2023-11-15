package event;

import domain.Customer;
import domain.DiscountEvent;
import domain.MenuItemsList;
import service.CustomerService;

public class GiftEvent extends DiscountEvent {

	private static final String GIFT_EVENT_NAME = "증정 이벤트";
	private static final int GIFT_NEED_PRICE = 120000;
	private static final MenuItemsList GIFT_TARGET = MenuItemsList.샴페인;
	private static final int GIFT_TARGET_NUMBER = 1;
	
	private final CustomerService customerService;

    public GiftEvent(CustomerService customerService) {
        super(GIFT_EVENT_NAME, 0);
        this.customerService = customerService;
    }

    @Override
    public boolean isApplicable(Customer customer) {
        int price = customerService.calculateTotalPrice(customer);
        return price >= GIFT_NEED_PRICE;
    }

	@Override
	public int calculateDiscountAmount(Customer customer) {
		int targetPrice = GIFT_TARGET.getPrice();
		return targetPrice;
	}

	public String getGiftTarget() {
		return GIFT_TARGET.getName() + " " + GIFT_TARGET_NUMBER;
	}

}