package oliviaproject.ui.selection.tile.color;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.ui.dashboard.DashBoardMenu_Fr;

public class SelectedTileColorPanel extends JPanel {
	JFrame myparent;
	Color colorPossible,colorSelect;
	public void init(JFrame parent) {
		myparent=parent;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JButton bcolor1 = new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Selected.Color1);
		JButton bcolor2 = new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Selected.Color2);
		JButton OKButton= new JButton(DashBoardMenu_Fr.MENUBAR.Preferences.Color.OK);
		SelectedTileColorPanel me = this;;
		this.add(bcolor1);
		bcolor1.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				 colorPossible = JColorChooser.showDialog(me,
						"Select a color", colorPossible);				
				 publishEvent();
			}});
		this.add(bcolor2);
		bcolor2.addActionListener(new AbstractAction() {


			@Override
			public void actionPerformed(ActionEvent e) {
				colorSelect= JColorChooser.showDialog(me,
						"Select a color", colorSelect);				
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
		ChessColorSelectEvent event=new ChessColorSelectEvent();
		event.setColorPossible(colorPossible);
		
		event.setColorSelect(colorSelect);
		DefaultConnection.getEventBus().publish(event);
	}
}
