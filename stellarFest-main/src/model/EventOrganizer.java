package model;

import java.util.List;

public class EventOrganizer extends User{
	List<Event> eventsCreated;

	public EventOrganizer(String userId, String userEmail, String userName, String userPassword, String userRole,
			List<Event> eventsCreated) {
		super(userId, userEmail, userName, userPassword, userRole);
		this.eventsCreated = eventsCreated;
	}

	public List<Event> getEventsCreated() {
		return eventsCreated;
	}

	public void setEventsCreated(List<Event> eventsCreated) {
		this.eventsCreated = eventsCreated;
	}
	
	
}
