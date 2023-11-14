package event;

import java.time.DayOfWeek;
import java.time.LocalDate;

import domain.Customer;
import domain.DiscountEvent;
import domain.DiscountWeekEvent;
import domain.MenuConst;
import domain.MenuItem;

public class WeekendDiscountEvent extends DiscountEvent {
	
    private static final String WEEKEND_DISCOUNT_NAME = "주말 할인";

    public WeekendDiscountEvent() {
        super(WEEKEND_DISCOUNT_NAME, 0);
    }

    @Override
    public boolean isApplicable(Customer customer) {
        int visitDate = customer.getVisitDate();
        LocalDate localDate = LocalDate.of(2023, 12, visitDate); 
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    @Override
    public int calculateDiscountAmount(Customer customer) {
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
