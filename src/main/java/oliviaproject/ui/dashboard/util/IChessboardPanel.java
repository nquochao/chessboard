package oliviaproject.ui.dashboard.util;

import java.awt.Color;

public interface IChessboardPanel {
	static final Color COLOR_TILE_BLACK = Color.DARK_GRAY;
	static final Color COLOR_TILE_WHITE = Color.WHITE;
	// rgba
	static final Color COLOR_TILE_SELECTED = new Color(0.9f, 0.9f, 0.f, 0.3f);
	static final Color COLOR_TILE_CLICK_SELECTED = new Color(0.3f, 0.3f, 0.9f, 0.2f);
	public static final int NUMBER_COLUMNS = 8;// x
	public static final int NUMBER_ROWS = 8;// y
	public static final int TILE_X = 100;// x
	public static final int TILE_Y = 100;// x
}
