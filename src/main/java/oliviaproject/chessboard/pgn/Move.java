package oliviaproject.chessboard.pgn;

import oliviaproject.ui.dashboard.util.Piece;

public class Move {
	static final String SEPARATOR=":";
	Piece piece;
	String from, to;
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Move(Piece piece, String from, String to) {
		this.piece=piece;
				this.from=from;
		this.to=to;
	}
	
	 private static void determineTo(String sanMove, Piece piece) {
	        if (piece == null) {
	            throw new NullPointerException("piece can't be null!");
	        }
	    }
@Override
public String toString() {
	return getPiece().name()+SEPARATOR+getFrom()+SEPARATOR+getTo();
}
}
