package oliviaproject.event;

import oliviaproject.ui.dashboard.IEventManager;

public class ChessEchelleEvent implements Event {
	int zoom;

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	@Override
	public void accept(IEventManager eventManager) {
		eventManager.visit(this);
	}
}
