package oliviaproject.chessboard.pgn;

public class GameStateMutable {
	GameHeader gameHeader;
public GameStateMutable() {

	gameHeader=new GameHeader();
}

	public GameHeader getGameHeader() {
		// TODO Auto-generated method stub
		return gameHeader;
	}


	public void setGameHeader(GameHeader gameHeader) {
		this.gameHeader=gameHeader;
	}

}
