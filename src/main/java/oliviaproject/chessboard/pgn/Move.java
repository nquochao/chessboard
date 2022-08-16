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
	
	 private static void determineTo(String sanMove, Piece piece) {
	        if (piece == null) {
	            throw new NullPointerException("piece can't be null!");
	        }
//	        Square to;

//	        switch (piece) {
//	            case WHITE_PAWN: //pro pesaky rozlisujem zda to je/neni promotion
//	            case BLACK_PAWN:
//	                if (sanMove.contains("=")) {
//	                    //je to promo => 4. - 3. znak od konce je "to"
//	                    to = Square.valueOf(
//	                            sanMove.substring(sanMove.length() - 4,
//	                            sanMove.length() - 2).toUpperCase());
//	                } else {
//	                    //kdyz to neni promotion posledni 2 znaky jsou "to"
//	                    to = Square.valueOf(
//	                            sanMove.substring(sanMove.length() - 2).toUpperCase());
//	                }
//	                break;
//	            case WHITE_KING: //pro ostatni figury je "to" vzdy urcen poslednimi
//	            case BLACK_KING: //dvemi znaky sanMoveu (s vyjimkou rosady)
//	            case WHITE_QUEEN:
//	            case BLACK_QUEEN:
//	            case WHITE_ROOK:
//	            case BLACK_ROOK:
//	            case WHITE_BISHOP:
//	            case BLACK_BISHOP:
//	            case WHITE_KNIGHT:
//	            case BLACK_KNIGHT:
//	                switch (sanMove) {
//	                    case "O-O":
//	                        to = (piece.isWhite() ? Square.G1 : Square.G8);
//	                        break;
//	                    case "O-O-O":
//	                        to = (piece.isWhite() ? Square.C1 : Square.C8);
//	                        break;
//	                    default:
//	                        to = Square.valueOf(sanMove.substring(sanMove.length() - 2).toUpperCase());
//	                        break;
//	                }
//	                break;
//	            default:
//	                throw new IllegalStateException("piece was not one of those"
//	                        + " defined in Chessmen enumeration!");
//	        }
//	        return to;
	    }

}
