package oliviaproject.ui.possiblemove;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.convertor.AbstractConvertor;
import oliviaproject.ui.position.Position;

public class PositionTour extends PositionRockPossible {
	protected static final Logger log = LoggerFactory.getLogger(PositionTour.class);

	public PositionTour() {
		super();

	}

	public PositionTour(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
	Position kingPosition=new Position(4, getY());
	// 3 is grand rock
	//5 is little rock
	Position targetPosition= new Position(getX()==0?3:5, this.getY());
	possibleRock.put(kingPosition.coordinate(), targetPosition);
}
	
	/**
	 *This is not implemented as it was for the Pion to allow +2 move at initiation
	 */
	/**
	 * x is the ordonate y at startup
	 * y is the offset to add to getY as possible move
	 */

	@Override
	public Position getPositionDepart() {
		// TODO Auto-generated method stub
		return null;
	}
}
