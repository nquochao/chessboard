package oliviaproject.chessboard.pgn;

import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.position.Position;

public class Move {
	Piece piece;
	Position from, to;
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public Position getFrom() {
		return from;
	}
	public void setFrom(Position from) {
		this.from = from;
	}
	public Position getTo() {
		return to;
	}
	public void setTo(Position to) {
		this.to = to;
	}
	public Move(Piece piece, Position from, Position to) {
		// TODO Auto-generated constructor stub
	}

}
