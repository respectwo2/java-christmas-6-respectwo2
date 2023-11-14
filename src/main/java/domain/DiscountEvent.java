package domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import event.ChristmasDdayDiscountEvent;
import event.GiftEvent;
import event.WeekdayDiscountEvent;
import event.WeekendDiscountEvent;

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

	public static int calculateTotalDiscount(Customer customer) {
		int totalDiscount = 0;
		List<DiscountEvent> events = customer.getEvents();

		for (DiscountEvent event : events) {
			event.calculateAndSetDiscountAmount(customer);
			totalDiscount += event.getDiscountAmount();
		}

		return totalDiscount;
	}

}
