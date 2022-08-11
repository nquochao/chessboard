package oliviaproject.ui.dashboard.scale;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * 
 * https://stackoverflow.com/questions/6543453/zooming-in-and-zooming-out-within-a-panel
 * 
 * @author DesktopPC
 *
 */
public class ScaleFrame extends JFrame {

	private int currentCard = 0;

	public void init() throws InterruptedException, IOException {
		ScalePanel panel = new ScalePanel();
		this.add(panel);
		panel.init(this);
		this.setSize(320, 320);

		pack();
		setVisible(true);
	}

}
