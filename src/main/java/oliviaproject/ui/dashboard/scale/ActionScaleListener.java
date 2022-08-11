package oliviaproject.ui.dashboard.scale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionScaleListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		ScaleFrame sc = new ScaleFrame();
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
