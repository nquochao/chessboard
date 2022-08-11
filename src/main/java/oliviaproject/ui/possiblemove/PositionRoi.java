package oliviaproject.ui.possiblemove;

import oliviaproject.ui.position.Position;

public class PositionRoi extends PositionRockPossible {

	/**
	 * 
	 */
	public PositionRoi() {
		super();

	}

	public PositionRoi(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This is not implemented as it was for the Pion to allow +2 move at initiation
	 */
	@Override
	public Position getPositionDepart() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void init(Position p) {
		// TODO Auto-generated method stub
		super.init(p);
		possibleMoves.add(new Position(0, 1));
		possibleMoves.add(new Position(-1, 1));
		possibleMoves.add(new Position(1, 1));
		possibleMoves.add(new Position(0, -1));
		possibleMoves.add(new Position(-1, -1));
		possibleMoves.add(new Position(1, -1));
		
		possibleMoves.add(new Position(-1, 0));
		possibleMoves.add(new Position(1, 0));
		/**
		 * 
		 * the coordinate y will be the King y position done in
		 * {@link getPossibleRock(Positions ps, Position position)}
		 */
		Position towerPosition=new Position(0, this.getY());
		Position targetPosition= new Position(getX()-2, this.getY());
		possibleRock.put(towerPosition.coordinate(), targetPosition);
		towerPosition=new Position(7, this.getY());
		targetPosition= new Position(getX()+2, this.getY());

		possibleRock.put(towerPosition.coordinate(), targetPosition);
		

	}
}
