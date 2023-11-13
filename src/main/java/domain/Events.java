package domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import event.Event;

public class Events {
	
    private List<Event> eventList;

    public Events() {
        this.eventList = new ArrayList<>();
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }
    
    public String toString(Customer customer) {
    	
        StringBuilder sb = new StringBuilder();
        Set<String> printedEvents = new HashSet<>();
        for (Event event : eventList) {
            String eventName = event.getEventName();
            DecimalFormat df = new DecimalFormat("###,###");
            String discountAmount = df.format(event.getDiscountAmount(customer));
            
            if (!printedEvents.contains(eventName)) {
                sb.append(eventName).append(": -").append(discountAmount).append("원\n");
                printedEvents.add(eventName);
            }
        }
        return sb.toString();
    }
    
}