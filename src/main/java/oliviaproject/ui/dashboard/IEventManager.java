package oliviaproject.ui.dashboard;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.ChessLoadPGNEvent;
import oliviaproject.event.ChessMoveEvent;
import oliviaproject.event.ChessPromotionEvent;
import oliviaproject.event.ChessSelectGame;

public interface IEventManager {
	public void visit(ChessColorPieceEvent event);

	public void visit(ChessColorDashBoardEvent event);

	public void visit(ChessColorSelectEvent event);

	public void visit(ChessMoveEvent event);
	public void visit(ChessEchelleEvent event);
	public void visit(ChessPromotionEvent event);

	public void visit(ChessLoadPGNEvent chessLoadPGNEvent);

	public void visit(ChessSelectGame chessSelectGame);

}
