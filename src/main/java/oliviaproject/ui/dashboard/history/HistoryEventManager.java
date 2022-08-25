package oliviaproject.ui.dashboard.history;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oliviaproject.chessboard.pgn.GameStateMutable;
import oliviaproject.chessboard.pgn.Move;
import oliviaproject.chessboard.pgn.PGNReader;
import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.ChessLoadPGNEvent;
import oliviaproject.event.ChessMoveEvent;
import oliviaproject.event.ChessPromotionEvent;
import oliviaproject.event.ChessSelectGame;
import oliviaproject.ui.dashboard.IEventManager;
import oliviaproject.ui.dashboard.color.HistoryPane;
import oliviaproject.util.file.FileUtils;

public class HistoryEventManager implements IEventManager {
	HistoryPane pane;
	public HistoryEventManager(HistoryPane historyPane) {
		this.pane=historyPane;
	}

	@Override
	public void visit(ChessColorPieceEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ChessColorDashBoardEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ChessColorSelectEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ChessMoveEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ChessEchelleEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ChessPromotionEvent event) {
		// TODO Auto-generated method stub

	}
	List<GameStateMutable> games;

	@Override
	public void visit(ChessLoadPGNEvent chessLoadPGNEvent) {
		String filePath=chessLoadPGNEvent.getPathFile();
		InputStream is = FileUtils.getFromPath(filePath);

		PGNReader reader = new PGNReader();
		games = reader.parseFile(is);
		GameNode n=new GameNode(new File(filePath).getName(), null);
		Iterator<GameStateMutable> it = games.iterator();
		while(it.hasNext()) {
		GameStateMutable g=it.next();
		
			GameNode game =createGame(n, g);
					pane.getGameMoves().tree.removeAll();
			//game =createGameMoves(g);
		}
		pane.getGameList().updateExample(new GameTreeModel(n));;
	
			
			
	}

	private GameNode createGame(GameNode parent, GameStateMutable game) {
        //the greatgrandparent generation
        GameNode a1 = new GameNode(game.getGameHeader().getDate()+game.getGameHeader().getSite()+game.getGameHeader().getWhite()+game.getGameHeader().getBlack(), game);

        //the grandparent generation
        GameNode b1 = new GameNode(game.getGameHeader().getWhite(), game);
        GameNode b2 = new GameNode(game.getGameHeader().getBlack(), game);
        GameNode b3 = new GameNode(game.getGameHeader().getDate(), game);
        GameNode b4 = new GameNode(game.getGameHeader().getEvent(), game);
        GameNode b5 = new GameNode(game.getGameHeader().getResult(), game);
        GameNode.linkFamily(parent,new GameNode[] {a1});
        GameNode.linkFamily(a1,new GameNode[] {b1,b2,b3,b4, b5});
		return a1;
		
	}

	private MoveNode createGameMoves(MoveNode parent, GameStateMutable game) {
        //the greatgrandparent generation
        MoveNode a1 = new MoveNode(game.getGameHeader().getDate()+game.getGameHeader().getSite()+game.getGameHeader().getWhite()+game.getGameHeader().getBlack(), game);
        List<MoveNode>games=new ArrayList<>();
        for(Move m:game.getMoves()) {
        	games.add(new MoveNode(m.getInitialMove(), game));
        }
        MoveNode[] myArray = new MoveNode[games.size()];
        games.toArray(myArray);
        MoveNode.linkFamily(parent,new MoveNode[] {a1});
        MoveNode.linkFamily(a1,myArray);
		return a1;
		
	}

	@Override
	public void visit(ChessSelectGame chessSelectGame) {
		GameStateMutable gameStateMutable =chessSelectGame.getGameStateMutable();
		MoveNode n=new MoveNode("", null);
		createGameMoves(n, chessSelectGame.getGameStateMutable());
		pane.getGameMoves().updateExample(new MoveTreeModel(n));;

		
	}	

}
