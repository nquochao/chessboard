package oliviaproject.ui.dashboard.color;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.ui.dashboard.DashBoardMenu_Fr;

public class PieceColorPanel extends JPanel {
	JFrame myparent;
	Color initialcolorWhite,initialcolorBlack;
	public void init(JFrame parent) {
		myparent=parent;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JButton bcolor1 = new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.Color1);
		JButton bcolor2 = new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.Color2);
		JButton OKButton= new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.OK);
		PieceColorPanel me = this;;
		this.add(bcolor1);
		bcolor1.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				 initialcolorWhite = JColorChooser.showDialog(me,
						"Select a color", initialcolorWhite);				
				 publishEvent();
			}});
		this.add(bcolor2);
		bcolor2.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				initialcolorBlack= JColorChooser.showDialog(me,
						"Select a color", initialcolorBlack);				
				publishEvent();
			}});
		OKButton.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				publishEvent();
				parent.setVisible(false);
			}});
		this.add(OKButton);

	}
	void publishEvent() {
		ChessColorPieceEvent event=new ChessColorPieceEvent();
		event.setColorWhite(initialcolorWhite);
		event.setColorBlack(initialcolorBlack);
		DefaultConnection.getEventBus().publish(event);
	}
}
