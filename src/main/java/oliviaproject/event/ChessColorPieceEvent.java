package oliviaproject.event;

import java.awt.Color;

import oliviaproject.ui.dashboard.IEventManager;

public class ChessColorPieceEvent implements Event {



	public Color getColorWhite() {
		return colorWhite;
	}

	public void setColorWhite(Color colorWhite) {
		this.colorWhite = colorWhite;
	}

	public Color getColorBlack() {
		return colorBlack;
	}

	public void setColorBlack(Color colorBlack) {
		this.colorBlack = colorBlack;
	}

	Color colorWhite,colorBlack;
	@Override
	public void accept(IEventManager eventManager) {
		eventManager.visit(this);
	}
}
