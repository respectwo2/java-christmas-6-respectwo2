package domain;

public class DiscountEvent {

	private String eventName;
	private int discountAmount;

	public DiscountEvent(String eventName, int discountAmount) {
		this.eventName = eventName;
		this.discountAmount = discountAmount;
	}

	public String getEventName() {
		return eventName;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public boolean isApplicable(Customer customer) {
		return false;
	}

	public int calculateDiscountAmount(Customer customer) {
		return 0;
	}

	public void calculateAndSetDiscountAmount(Customer customer) {
		this.discountAmount = calculateDiscountAmount(customer);
	}

}
