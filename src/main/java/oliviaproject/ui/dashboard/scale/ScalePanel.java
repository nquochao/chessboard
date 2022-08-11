package oliviaproject.ui.dashboard.scale;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.ui.dashboard.DashBoardMenu_Fr;

public class ScalePanel extends JPanel {
	JFrame myparent;
	public void init(JFrame parent) {
		myparent=parent;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel label = new JLabel(DashBoardMenu_Fr.MENUBAR.Preferences.SCALE);
		JTextField textField= new JTextField();
		
		this.add(label);
		this.add(textField);
        final String enterKey = "VK_ENTER";
        KeyStroke enter = KeyStroke.getKeyStroke("pressed ENTER");
        textField.getInputMap().put(enter, enterKey);
        Action enterAction= new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String zoom = textField.getText();
				try {
					Integer zoomInt = Integer.valueOf(zoom);
					ChessEchelleEvent event = new ChessEchelleEvent();
					event.setZoom(zoomInt);
					DefaultConnection.getEventBus().publish(event);
					myparent.setVisible(false);
					
				} catch (Exception ex) {
					
				}
			}};;
		textField.getActionMap().put(enterKey, enterAction);

	};
}
