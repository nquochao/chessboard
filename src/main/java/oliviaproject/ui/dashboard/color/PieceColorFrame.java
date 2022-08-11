package oliviaproject.ui.dashboard.color;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * 
 * https://stackoverflow.com/questions/6543453/zooming-in-and-zooming-out-within-a-panel
 * 
 * @author DesktopPC
 *
 */
public class PieceColorFrame extends JFrame {

	private int currentCard = 0;

	public void init() throws InterruptedException, IOException {
		PieceColorPanel panel = new PieceColorPanel();
		this.add(panel);
		panel.init(this);
		this.setSize(320, 320);

		pack();
		setVisible(true);
	}

}
