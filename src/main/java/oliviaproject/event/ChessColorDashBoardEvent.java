package oliviaproject.event;

import java.awt.Color;

public class ChessColorDashBoardEvent implements Event {
	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = color1;
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
	}

	Color color1,color2;



}
