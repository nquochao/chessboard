package oliviaproject.eventbus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import oliviaproject.event.Event;

public class EventBus {
	Map<String, Set<EventListener>> eventMap= new HashMap<>();
	public void subscribe(EventListener id, Event event) {
		Set<EventListener> subscribers=eventMap.get(event.getClass().getName());
		if(subscribers==null) {
			subscribers=new HashSet<>();
			eventMap.put(event.getClass().getName(), subscribers);
		}
		subscribers.add(id);
	};
	public void unsubscribe(EventListener id, Event event) {
		Set<EventListener> subscribers=eventMap.get(event);
		subscribers.remove(id);
	};
	public void publish(Event event) {
		Set<EventListener> subscribers=eventMap.get(event.getClass().getName());
		for(EventListener subscriber:subscribers) {
			subscriber.onMyEvent(event);
		}
	};
}
