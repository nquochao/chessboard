package oliviaproject.ui.piece.color;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.ui.dashboard.DashBoardMenu_Fr;

public class DashBoardColorPanel extends JPanel {
	JFrame myparent;
	Color initialcolor1,initialcolor2;
	public void init(JFrame parent) {
		myparent=parent;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JButton bcolor1 = new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.Color1);
		JButton bcolor2 = new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.Color2);
		JButton OKButton= new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.OK);
		DashBoardColorPanel me = this;;
		this.add(bcolor1);
		bcolor1.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				 initialcolor1 = JColorChooser.showDialog(me,
						"Select a color", initialcolor1);				
			}});
		this.add(bcolor2);
		bcolor2.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				initialcolor2= JColorChooser.showDialog(me,
						"Select a color", initialcolor2);				
			}});
		OKButton.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				ChessColorDashBoardEvent event=new ChessColorDashBoardEvent();
				event.setColor1(initialcolor1);
				event.setColor2(initialcolor2);
				DefaultConnection.getEventBus().publish(event);
				parent.setVisible(false);
			}});
		this.add(OKButton);

	}
}
