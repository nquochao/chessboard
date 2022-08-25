package oliviaproject.event;

import oliviaproject.ui.dashboard.IEventManager;

public class ChessLoadPGNEvent implements Event{
String pathFile;

public String getPathFile() {
	return pathFile;
}

public void setPathFile(String pathFile) {
	this.pathFile = pathFile;
}

@Override
public void accept(IEventManager eventManager) {
	eventManager.visit(this);
}
}
