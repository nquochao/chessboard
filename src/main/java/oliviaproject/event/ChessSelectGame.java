package oliviaproject.event;

import oliviaproject.chessboard.pgn.GameStateMutable;
import oliviaproject.ui.dashboard.IEventManager;

public class ChessSelectGame implements Event {
	GameStateMutable gameStateMutable;

	public GameStateMutable getGameStateMutable() {
		return gameStateMutable;
	}

	@Override
	public void accept(IEventManager eventManager) {
		eventManager.visit(this);
	}

	public void setGameStateMutable(GameStateMutable gameStateMutable) {
		this.gameStateMutable = gameStateMutable;
	}

}
