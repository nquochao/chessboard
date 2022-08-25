package oliviaproject.ui.selection.tile.color.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.GameStateMutable;
import oliviaproject.chessboard.pgn.Move;
import oliviaproject.event.ChessMoveEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.ui.dashboard.OliviaPanel;
import oliviaproject.ui.position.Position;

public class KeyboardDemo2 extends KeyboardDemo {
	static final Logger log = LoggerFactory.getLogger(KeyboardDemo2.class);

	public KeyboardDemo2(JPanel panel, String filePath, Color[] colors) {
		super(panel, filePath, colors);
	}

	public Map<String, MoveObject> createMovedObjects(String title, Color[] colors) {
		Map<String, MoveObject> result = new HashMap<>();
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		int colorint = getRandomNumber(0, colors.length - 1);
		int x = 100, y = 100;
		if (title == null)
			title = "";
		String[] titles = title.split(" ");
		StringBuilder line = new StringBuilder();
		List<String> lines = new ArrayList<>();
		for (int i = 0; i < titles.length; i++) {
			String mot = titles[i];
			boolean doAppend = line.length() + mot.length() <= 20;
			if (doAppend)
				line.append(mot);
			else {
				lines.add(line.toString());

				line = new StringBuilder();
				line.append(mot);
			}
			line.append(" ");
		}
		lines.add(line.toString());
		for (int i = 0; i < lines.size(); i++) {
			String l = lines.get(i);
			result.put(l, new MoveObject(x, y, getRandomNumber(0, 20), getRandomNumber(0, 20), l, colors[colorint],
					colors, 0, panel.getWidth(), 0, panel.getHeight(), font));
			y = y + 100;
		}

		this.movedObjects = result;
		return result;
	}

	@Override
	public void initializeKeyboard() {
		super.initializeKeyboard();

		final String key = "VK_TAB";
		KeyStroke kb = KeyStroke.getKeyStroke("RIGHT");
		panel.getInputMap().put(kb, key);
		Action moveAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					gameMove();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		;

		panel.getActionMap().put(key, moveAction);
		reInitializeKeyboard();

	}


	public void gameMove() {
		Iterator<GameStateMutable> it = null;
		if (moveNumber < game.getMoves().size()) {
			Move m = game.getMoves().get(moveNumber);
			ChessMoveEvent event = m.defineEvent();
			DefaultConnection.getEventBus().publish(event);
			moveNumber++;
		}

	}

	void checkRoc() {
		OliviaPanel p = ((OliviaPanel) panel);
		String[] tourCoords = new String[] { "7,7", "0,0", "0,7", "7,0" };
		for (String coord : tourCoords) {
			Position x = p.getPs().get(coord);
			x.getPiece().getPossibleMove().init(x);
			String roiCoord = "4,0";
			if (coord.endsWith("7"))
				roiCoord = "4,7";
			Position tourRockTarget = x.getPiece().getPossibleMove().getPossibleRock().get(roiCoord);
			if (tourRockTarget == null)
				continue;
			String coordinate = tourRockTarget.coordinate();
			log.info("rock possible for tour " + coord + "is:" + coordinate);
		}
	}

}
