package oliviaproject.eventbus;

import java.awt.Color;

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



}
