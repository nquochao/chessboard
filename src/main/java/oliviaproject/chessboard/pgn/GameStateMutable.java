package oliviaproject.chessboard.pgn;

import java.util.ArrayList;
import java.util.List;

public class GameStateMutable {
	GameHeader gameHeader;
	List<Move> moves;
public List<Move> getMoves() {
		return moves;
	}


	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}


public GameStateMutable() {

	gameHeader=new GameHeader();
	moves=new ArrayList<>();
}

	
	public GameHeader getGameHeader() {
		// TODO Auto-generated method stub
		return gameHeader;
	}


	public void setGameHeader(GameHeader gameHeader) {
		this.gameHeader=gameHeader;
	}
	

}
