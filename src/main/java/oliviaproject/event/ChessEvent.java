package oliviaproject.event;

import oliviaproject.ui.position.Position;
import oliviaproject.ui.promotion.ChessPiecePromotion;

public class ChessEvent implements Event {
	ChessPiecePromotion promotion;
	Position position;
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public ChessPiecePromotion getPromotion() {
		return promotion;
	}
	public void setPromotion(ChessPiecePromotion promotion) {
		this.promotion = promotion;
	}


}
