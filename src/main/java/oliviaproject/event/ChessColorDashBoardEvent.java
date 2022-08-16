package oliviaproject.event;

import java.awt.Color;

public class ChessColorDashBoardEvent implements Event {
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



}
