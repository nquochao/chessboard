package oliviaproject.event;

import oliviaproject.ui.dashboard.IEventManager;

public abstract class AbstractEvent implements Event {

	@Override
	public abstract void accept(IEventManager eventManager);

}
