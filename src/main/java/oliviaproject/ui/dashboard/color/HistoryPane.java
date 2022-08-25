package oliviaproject.ui.dashboard.color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import oliviaproject.chessboard.pgn.GameStateMutable;
import oliviaproject.event.ChessLoadPGNEvent;
import oliviaproject.event.DefaultConnection;
import oliviaproject.event.Event;
import oliviaproject.eventbus.EventListener;
import oliviaproject.ui.dashboard.IEventManager;
import oliviaproject.ui.dashboard.history.GameTreeExample;
import oliviaproject.ui.dashboard.history.HistoryEventManager;
import oliviaproject.ui.dashboard.history.MoveTreeExample;

public class HistoryPane  implements EventListener{
	IEventManager eventManager=new HistoryEventManager(this);
	
	GameTreeExample gameList ;
	MoveTreeExample gameMoves ; 
	public MoveTreeExample getGameMoves() {
		return gameMoves;
	}
	public void setGameMoves(MoveTreeExample gameMoves) {
		this.gameMoves = gameMoves;
	}
	JSplitPane splitPane ;
	List<GameStateMutable> games;
	GameStateMutable game;
	int moveNumber,numGame;

	public HistoryPane() {
		JPanel panel=new JPanel();
		JButton load= new JButton("Load");
		JLabel label = new JLabel();
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser j = new JFileChooser();
				 
				// Open the save dialog
				int r=j.showSaveDialog(null);

				if (r == JFileChooser.APPROVE_OPTION)
					 
		            {
					String path=j.getSelectedFile().getAbsolutePath();
		                createChessEventFile(path);
		                label.setText(j.getSelectedFile().getName());
		            }
		            else
		                label.setText("the user cancelled the operation");
			}});
		gameList =(GameTreeExample) createGameList();
	
		gameMoves = (MoveTreeExample)createGameMoves();
	
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, gameMoves);
		panel.add(gameList);
		panel.add(load);
		panel.add(label);
	}
	public void createChessEventFile(String path) {

			ChessLoadPGNEvent event = new ChessLoadPGNEvent();
			event.setPathFile(path);
			DefaultConnection.getEventBus().publish(event);
	}

	public JSplitPane getPanel() {
		return splitPane;
	}
	@Override
	public void onMyEvent(Event event) {
		event.accept(eventManager);
	}
    /**
     * @return
     */
    public JPanel createGameMoves() {

    return new MoveTreeExample();
    }
    /**
     * @return
     */
    public JPanel createGameList() {

    return new GameTreeExample();
    }
	public GameTreeExample getGameList() {
		return gameList;
	}
     
	
}
