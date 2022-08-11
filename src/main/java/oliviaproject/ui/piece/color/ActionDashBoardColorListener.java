package oliviaproject.ui.piece.color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionDashBoardColorListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		DashBoardColorFrame sc = new DashBoardColorFrame();
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
