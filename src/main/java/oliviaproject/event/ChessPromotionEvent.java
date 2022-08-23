package oliviaproject.event;

import oliviaproject.ui.dashboard.IEventManager;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.promotion.ChessPiecePromotion;

public class ChessPromotionEvent implements Event {
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
	@Override
	public void accept(IEventManager eventManager) {
		eventManager.visit(this);
	}

}
