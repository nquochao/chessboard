package oliviaproject.ui.dashboard;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

import org.springframework.beans.factory.annotation.Autowired;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.ChessLoadPGNEvent;
import oliviaproject.event.ChessMoveEvent;
import oliviaproject.event.ChessPromotionEvent;
import oliviaproject.event.ChessSelectGame;
import oliviaproject.event.DefaultConnection;
import oliviaproject.hibernate.manager.SaveUserNameManager;
import oliviaproject.ui.dashboard.color.ActionPieceColorListener;
import oliviaproject.ui.dashboard.color.HistoryPane;
import oliviaproject.ui.dashboard.scale.ActionScaleListener;
import oliviaproject.ui.dashboard.util.PlayMode;
import oliviaproject.ui.piece.color.ActionDashBoardColorListener;
import oliviaproject.ui.selection.tile.color.ActionSelectedTileColorListener;
import oliviaproject.ui.selection.tile.color.demo.DemoMenuItems;

public class OliviaFrame extends JFrame {
	@Autowired
	SaveUserNameManager saveManager;
	private int currentCard = 0;
	OliviaPanel pane = new OliviaPanel();
	HistoryPane historyPane ;
	Long duration = 300L;

	public void init() throws InterruptedException, IOException {

		this.setSize(320, 320);
	
		DefaultConnection.getEventBus().subscribe(pane, new ChessPromotionEvent());
		DefaultConnection.getEventBus().subscribe(pane, new ChessEchelleEvent());
		DefaultConnection.getEventBus().subscribe(pane, new ChessColorDashBoardEvent());
		DefaultConnection.getEventBus().subscribe(pane, new ChessColorPieceEvent());
		DefaultConnection.getEventBus().subscribe(pane, new ChessColorSelectEvent());
		DefaultConnection.getEventBus().subscribe(pane, new ChessMoveEvent());
		DefaultConnection.getEventBus().subscribe(pane, new ChessSelectGame());

		DefaultConnection.getEventBus().subscribe(saveManager, new ChessPromotionEvent());
		DefaultConnection.getEventBus().subscribe(saveManager, new ChessEchelleEvent());
		DefaultConnection.getEventBus().subscribe(saveManager, new ChessColorDashBoardEvent());
		DefaultConnection.getEventBus().subscribe(saveManager, new ChessColorPieceEvent());
		DefaultConnection.getEventBus().subscribe(saveManager, new ChessColorSelectEvent());
		pane.setAddCoordinates(true);
		pane.setPlayMode(PlayMode.game);
		pane.initialize();
		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		historyPane=new HistoryPane();
		DefaultConnection.getEventBus().subscribe(historyPane, new ChessLoadPGNEvent());
		DefaultConnection.getEventBus().subscribe(historyPane, new ChessSelectGame());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, historyPane.getPanel());
		// Hide left or top
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(80*9);

		setMenuBar(createMenuBar());
		// used to get content pane
		getContentPane().add(splitPane, BorderLayout.CENTER);
		// Creating Object of "JPanel" class

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.setFocusable(true);
		pane.requestFocusInWindow();
	}

	protected MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu(DashBoardMenu_Fr.MENUBAR.PREFERENCES);
		MenuItem menuScale = new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.SCALE);
		Menu menuDashBoard = new Menu(DashBoardMenu_Fr.MENUBAR.Preferences.Dashboard);
		MenuItem menuDashBoardColor = new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.Color.Color);
		menuDashBoard.add(menuDashBoardColor);
		Menu menuPiece = new Menu(DashBoardMenu_Fr.MENUBAR.Preferences.Pieces);
		MenuItem menuPieceColor = new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.Pieces);

		menuPiece.add(menuPieceColor);

		MenuItem menuColorSelectionItem = new MenuItem(DashBoardMenu_Fr.MENUBAR.Preferences.SelectedTileColor);

		createDemoMenu(menu, duration);

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

	@Autowired
	DemoMenuItems demoMenus;

	protected void createDemoMenu(Menu menu, Long durationDashboard) {
	}

}
