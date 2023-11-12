package domain;

import java.util.ArrayList;
import java.util.List;

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
}