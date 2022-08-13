package oliviaproject.event;

import java.awt.Color;

public class ChessColorSelectEvent implements Event {



	Color colorPossible,colorSelect;

	public Color getColorPossible() {
		return colorPossible;
	}

	public void setColorPossible(Color colorPossible) {
		this.colorPossible = colorPossible;
	}

	public Color getColorSelect() {
		return colorSelect;
	}

	public void setColorSelect(Color colorSelect) {
		this.colorSelect = colorSelect;
	}





}
