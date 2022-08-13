package oliviaproject.ui.selection.tile.color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionSelectedTileColorListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		SelectedTileColorFrame sc = new SelectedTileColorFrame();
		try {
			sc.init();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
