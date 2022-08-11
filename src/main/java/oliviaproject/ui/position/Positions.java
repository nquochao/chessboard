package oliviaproject.ui.position;

import java.util.HashMap;

import oliviaproject.ui.dashboard.util.Side;
import oliviaproject.ui.position.initializer.IPositionsInitializer;
import oliviaproject.ui.position.initializer.PositionsInitializer;
import oliviaproject.ui.position.initializer.TestPositionInitializerPions;

public class Positions extends HashMap<String, Position> {
	IPositionsInitializer initializer;
		public Positions() {
			super();
			//initializer=new TestPositionsInitializerBigPiecesOnly(this);
			//initializer=new TestPositionsInitializerRock(this);
			initializer=new TestPositionInitializerPions(this);
			initializer=new PositionsInitializer(this);
		}

	/**
	 * @param s
	 */
	public void initialize(Side s) {
		initializer.initialize(s);
	}

}
