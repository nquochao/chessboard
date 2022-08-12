package oliviaproject.eventbus;

import java.awt.Color;

public class ChessColorDashBoardEvent implements Event {
	public Color getColorWhiteTile() {
		return colorWhiteTile;
	}

	public void setColorWhiteTile(Color color1) {
		this.colorWhiteTile = color1;
	}

	public Color getColorBlackTile() {
		return colorBlackTile;
	}

	public void setColorBlackTile(Color color2) {
		this.colorBlackTile = color2;
	}

	Color colorWhiteTile,colorBlackTile;



}
