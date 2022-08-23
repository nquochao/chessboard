package oliviaproject.event;

import java.awt.Color;

import oliviaproject.ui.dashboard.IEventManager;

public class ChessColorDashBoardEvent extends AbstractEvent implements Event {
	public Color getColorWhite() {
		return colorWhite;
	}

	public void setColorWhite(Color color1) {
		this.colorWhite = color1;
	}

	public Color getColorBlack() {
		return colorBlack;
	}

	public void setColorBlack(Color color2) {
		this.colorBlack = color2;
	}

	Color colorWhite,colorBlack;

	@Override
	public void accept(IEventManager eventManager) {
		eventManager.visit(this);
	}



}
