package event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import domain.Customer;
import domain.DiscountWeekEvent;
import domain.Events;
import domain.MenuConst;
import domain.MenuItem;

public class WeekdayDiscountEvent implements Event {
	
    private static final String WEEKDAY_DISCOUNT_NAME = "평일 할인";
    private Events events;

    public WeekdayDiscountEvent(Events events) {
        this.events = events;
    }

    @Override
    public void applyEvent(Customer customer) {
        int visitDate = customer.getVisitDate();
        LocalDate localDate = LocalDate.of(2023, 12, visitDate); 
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
            events.addEvent(this);
        }
    }
 
    @Override
    public String getEventName() {
        return WEEKDAY_DISCOUNT_NAME;
    }

    public int getDiscountAmount(Customer customer) {
        int discountAmount = 0;
        DiscountWeekEvent discountEvent = DiscountWeekEvent.getDiscountEvent(customer);
        
        for (MenuItem menuItem : customer.getMenuItems()) {
        	String menuType = MenuConst.valueOf(menuItem.getMenu()).getType();
        	if (menuType.equals(discountEvent.getMenuType())) {
                discountAmount += menuItem.getQuantity() * discountEvent.getDiscountAmount();
            }
        }
        return discountAmount;
    }
}