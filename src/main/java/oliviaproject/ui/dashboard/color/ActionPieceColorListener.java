package oliviaproject.ui.dashboard.color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionPieceColorListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		PieceColorFrame sc = new PieceColorFrame();
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
