package oliviaproject.ui.possiblemove;

import oliviaproject.ui.position.Position;

public class PositionReine extends Position {

public PositionReine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PositionReine(int x, int y) {
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
		for(int i=0;i<8;i++) {
			possibleMoves.add(new Position(i,-i));
			possibleMoves.add(new Position(-i,i));
			possibleMoves.add(new Position(i,i));
			possibleMoves.add(new Position(-i,-i));

		}
	}
}
