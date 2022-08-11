package oliviaproject.ui.possiblemove;

import oliviaproject.ui.position.Position;

public class PositionPionB extends PositionPion {
	public PositionPionB() {
		super();

	}

	public PositionPionB(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * x is the ordonate y at startup
	 * y is the offset to add to getY as possible move
	 */
	@Override
	public Position getPositionDepart() {
		// TODO Auto-generated method stub
		return new Position( 1,2);
	}

	@Override
	public void init(Position p) {
		// TODO Auto-generated method stub
		super.init(p);

		possibleMoves.add(new Position(0, 1));
		possibleMovesEating.add(new Position(1, 1));
		possibleMovesEating.add(new Position(-1, 1));
		/**
		 * 
		 * we will update with the getX+possibleMovesEatingEnPassant.getX the Position x so I put -1 and 1
		 * so x is an offset while y is absolute position
		 */
		possibleMovesEatingEnPassant.add(new Position(-1, 5));
		possibleMovesEatingEnPassant.add(new Position(1, 5));

	}

}