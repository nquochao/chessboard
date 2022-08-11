package oliviaproject.ui.possiblemove;

import oliviaproject.ui.position.Position;

public class PositionPionW extends PositionPion {
	




	
	public PositionPionW() {
		super();
	}
	public PositionPionW(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


@Override
public Position getPositionDepart() {
	// TODO Auto-generated method stub
	return new Position(6,-2);
}
@Override
public void init(Position p) {
	// TODO Auto-generated method stub
	super.init(p);
	possibleMoves.add(new Position(0,-1));
	possibleMovesEating.add(new Position(1,-1));
	possibleMovesEating.add(new Position(-1,-1));
	
	/**
	 * 
	 * we will update with the getX+possibleMovesEatingEnPassant.getX the Position x so I put -1 and 1
	 * so x is an offset while y is absolute position
	 */
	possibleMovesEatingEnPassant.add(new Position(-1, 2));
	possibleMovesEatingEnPassant.add(new Position(1, 2));

}
}
