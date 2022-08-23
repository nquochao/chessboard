package oliviaproject.event;

import oliviaproject.ui.dashboard.IEventManager;

public interface Event {

	void accept(IEventManager eventManager);

}
