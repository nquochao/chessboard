package oliviaproject.ui.selection.tile.color.demo;

import static oliviaproject.ui.selection.tile.color.demo.DemoColorUtil.*;

import java.awt.Color;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.event.Event;

public class DemoSelectColorCallable implements Runnable {

	@Override
	public void run() {
		Event event = randomColorSelectColors();
		DefaultConnection.getEventBus().publish(event);

	}

	private ChessColorSelectEvent randomColorSelectColors() {
		ChessColorSelectEvent event = new ChessColorSelectEvent();
		Color colorWhite = createTransparentPastelRandomColor();
		Color colorBlack = createTransparentPastelRandomColor();
		event.setColorSelect(colorBlack);
		event.setColorPossible(colorWhite);

		return event;
	}

	private ChessColorPieceEvent randomPieceColors() {
		ChessColorPieceEvent event = new ChessColorPieceEvent();
		Color colorWhite = createPastelRandomColor();
		Color colorBlack = createRandomColor();
		event.setColorBlack(colorBlack);
		event.setColorWhite(colorWhite);

		return event;
	}

	private ChessColorDashBoardEvent randomDashboardColors() {
		ChessColorDashBoardEvent event = new ChessColorDashBoardEvent();
		Color colorWhite = createPastelRandomColor();
		Color colorBlack = createRandomColor();
		event.setColorBlack(colorBlack);
		event.setColorWhite(colorWhite);

		return event;
	}
}
