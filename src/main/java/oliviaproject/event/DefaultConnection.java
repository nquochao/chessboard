package oliviaproject.event;

public class DefaultConnection {
	final static EventBus eventBus= new EventBus();
public static EventBus getEventBus() {
	return eventBus;
}
}
