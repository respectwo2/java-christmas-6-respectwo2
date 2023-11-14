package domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public enum DiscountWeekEvent {
    MONDAY("평일 할인", "디저트", 2023),
    TUESDAY("평일 할인", "디저트", 2023),
    WEDNESDAY("평일 할인", "디저트", 2023),
    THURSDAY("평일 할인", "디저트", 2023),
    FRIDAY("주말 할인", "메인", 2023),
    SATURDAY("주말 할인", "메인", 2023),
    SUNDAY("평일 할인", "디저트", 2023);

    private final String eventName;
    private final String menuType;
    private final int discountAmount;

    DiscountWeekEvent(String eventName, String menuType, int discountAmount) {
        this.eventName = eventName;
        this.menuType = menuType;
        this.discountAmount = discountAmount;
    }

    public String getEventName() {
        return eventName;
    }

    public String getMenuType() {
        return menuType;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public static DiscountWeekEvent getDiscountEvent(Customer customer) {
        int visitDate = customer.getVisitDate();
        LocalDate localDate = LocalDate.of(2023, 12, visitDate); 
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return DiscountWeekEvent.valueOf(dayOfWeek.name());

    }
}