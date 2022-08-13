package oliviaproject.ui.dashboard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.ChessEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.hibernate.manager.SaveUserNameManager;
import oliviaproject.ui.dashboard.color.ActionPieceColorListener;
import oliviaproject.ui.dashboard.scale.ActionScaleListener;
import oliviaproject.ui.dashboard.util.PlayMode;
import oliviaproject.ui.demo.TextDemo;
import oliviaproject.ui.piece.color.ActionDashBoardColorListener;
import oliviaproject.ui.selection.tile.color.ActionSelectedTileColorListener;
@Component
public class OliviaFrame extends JFrame{
	@Autowired
	SaveUserNameManager saveManager;
	private int currentCard=0;
	public void init() throws InterruptedException, IOException {

	this.setSize(320, 320);
	CardLayout cl = new CardLayout();
	JPanel cardPanel = new JPanel();
	cardPanel.setLayout(cl);
	OliviaPanel pane= new OliviaPanel();
	JScrollPane scroll = new JScrollPane(pane);
	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	this.add(scroll);
	DefaultConnection.getEventBus().subscribe(pane, new ChessEvent());
	DefaultConnection.getEventBus().subscribe(pane, new ChessEchelleEvent());
	DefaultConnection.getEventBus().subscribe(pane, new ChessColorDashBoardEvent());
	DefaultConnection.getEventBus().subscribe(pane, new ChessColorPieceEvent());
	DefaultConnection.getEventBus().subscribe(pane, new ChessColorSelectEvent());

	DefaultConnection.getEventBus().subscribe(saveManager, new ChessEvent());
	DefaultConnection.getEventBus().subscribe(saveManager, new ChessEchelleEvent());
	DefaultConnection.getEventBus().subscribe(saveManager, new ChessColorDashBoardEvent());
	DefaultConnection.getEventBus().subscribe(saveManager, new ChessColorPieceEvent());
	DefaultConnection.getEventBus().subscribe(saveManager, new ChessColorSelectEvent());

	cardPanel.add(scroll, "0");
	pane.setAddCoordinates(true);
	pane.setPlayMode(PlayMode.game);
	pane.initialize();
	
	
	setMenuBar(createMenuBar());
    JPanel testPanel=new TextDemo();
	cardPanel.add(testPanel, "1");
	// used to get content pane
	getContentPane().add(cardPanel, BorderLayout.NORTH);
	// Creating Object of "JPanel" class
			JPanel buttonPanel = new JPanel();

			// Initialization of object
			// "firstbtn" of JButton class.
			JButton firstBtn = new JButton("Save");
			buttonPanel.add(firstBtn);
			firstBtn.addActionListener(new ActionListener()
			{

				public void actionPerformed(ActionEvent arg0)
				{
					
						
						// increment the value of currentcard by 1
						currentCard += 1;
						currentCard = currentCard %2;

						// show the value of currentcard
						cl.show(cardPanel, "" + (currentCard));
						
					
					
				}
			});
	// used to get content pane
	// that was for fun only getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    pack();
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private MenuBar createMenuBar() {
		MenuBar menuBar=new MenuBar();
		Menu menu = new Menu(DashBoardMenu_Fr.MENUBAR.PREFERENCES);
		MenuItem menuScale= new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.SCALE);
		Menu menuDashBoard= new Menu(DashBoardMenu_Fr.MENUBAR.Preferences.Dashboard);
		MenuItem menuDashBoardColor= new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.Color.Color);
		menuDashBoard.add(menuDashBoardColor);
		Menu menuPiece= new Menu(DashBoardMenu_Fr.MENUBAR.Preferences.Pieces);
		MenuItem menuPieceColor= new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.Pieces);
	
		menuPiece.add(menuPieceColor);

		MenuItem menuColorSelectionItem= new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.SelectedTileColor);

		menu.add(menuScale);
		menu.add(menuDashBoardColor);
		menu.add(menuPieceColor);
		menu.add(menuColorSelectionItem);
		menuScale.addActionListener(new ActionScaleListener());
		menuDashBoardColor.addActionListener(new ActionDashBoardColorListener());
		menuPieceColor.addActionListener(new ActionPieceColorListener());
		menuColorSelectionItem.addActionListener(new ActionSelectedTileColorListener());
		menuBar.add(menu);
		return menuBar;
	}

}
