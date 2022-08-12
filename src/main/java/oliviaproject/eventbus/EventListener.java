package oliviaproject.eventbus;

import oliviaproject.event.Event;

public interface EventListener
{
    public void onMyEvent(Event event);
}