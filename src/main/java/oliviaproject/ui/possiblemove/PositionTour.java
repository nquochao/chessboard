package oliviaproject.ui.possiblemove;

import oliviaproject.ui.position.Position;

public class PositionTour extends PositionRockPossible {
	public PositionTour() {
		super();

	}

	public PositionTour(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	/**
	 *This is not implemented as it was for the Pion to allow +2 move at initiation
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
	for(int i=0;i<8;i++) {
		possibleMoves.add(new Position(0,i));
		possibleMoves.add(new Position(i,0));

	}
	for(int i=0;i<8;i++) {
		possibleMoves.add(new Position(0,-i));
		possibleMoves.add(new Position(-i,0));

	}
	
	/**
	 * 
	 * the coordinate y will be the King y position done in
	 * {@link getPossibleRock(Positions ps, Position position)}
	 */
	Position kingPosition=new Position(4, this.getY());
	// 3 is grand rock
	//5 is little rock
	Position targetPosition= new Position(getX()==0?3:5, this.getY());
	possibleRock.put(kingPosition.coordinate(), targetPosition);
}
}
