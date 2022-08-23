package oliviaproject.event;

import oliviaproject.chessboard.pgn.Move;
import oliviaproject.ui.dashboard.IEventManager;

public class ChessMoveEvent implements Event {
	Move move;
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move=move;
	}
	@Override
	public void accept(IEventManager eventManager) {
		eventManager.visit(this);
	}
}
