package oliviaproject.event;

import oliviaproject.eventbus.EventBus;

public class DefaultConnection {
	final static EventBus eventBus= new EventBus();
public static EventBus getEventBus() {
	return eventBus;
}
}
