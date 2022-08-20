package oliviaproject.ui.selection.tile.color.demo;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class KeyboardDemo2 extends KeyboardDemo {

	public KeyboardDemo2(JPanel panel, String filePath, Color[] colors) {
		super(panel, filePath, colors);
		// TODO Auto-generated constructor stub
	}

	public Map<String, MoveObject> createMovedObjects(String title, Color[] colors) {
		Map<String, MoveObject> result = new HashMap<>();
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		int colorint = getRandomNumber(0, colors.length - 1);
		int x = 100, y = 100;
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
			String l=lines.get(i);
			result.put(l, new MoveObject(x, y, getRandomNumber(0, 20), getRandomNumber(0, 20), l,
					colors[colorint], colors, 0, panel.getWidth(), 0, panel.getHeight(), font));
			y= y+100;
		}

		this.movedObjects = result;
		return result;
	}
}
