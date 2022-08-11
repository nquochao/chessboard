package oliviaproject.ui.possiblemove;

import java.util.HashSet;
import java.util.Set;

import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;

public class PositionCavalier extends Position {
	public PositionCavalier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PositionCavalier(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}



protected Set<String> checkPieceBetween(Set<String> result,Positions ps, Position position) {
	Set<String> result2 =new HashSet<>();

	for(String r:result) {
		if(ps.get(r).getPiece().getSide()!=position.getPiece().getSide()){
			result2.add(r) ;
		}
	}		return result2;
}
@Override
public void init(Position p) {
	// TODO Auto-generated method stub
	super.init(p);

	possibleMoves.add(new Position(1,2));
	possibleMoves.add(new Position(1,-2));
	possibleMoves.add(new Position(-1,2));
	possibleMoves.add(new Position(-1,-2));
	possibleMoves.add(new Position(2,1));
	possibleMoves.add(new Position(2,-1));
	possibleMoves.add(new Position(-2,1));
	possibleMoves.add(new Position(-2,-1));
}
}
