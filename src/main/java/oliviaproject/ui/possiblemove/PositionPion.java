package oliviaproject.ui.possiblemove;

import java.util.HashSet;
import java.util.Set;

import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;

public abstract class PositionPion extends Position {
	public abstract Position getPositionDepart();

	public PositionPion() {
		super();// TODO Auto-generated constructor stub
	}

	public PositionPion(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	protected Set<String> caseDepart(Position position, Positions ps) {
		Set<String> result = new HashSet<>();
		Position possiblePosition = new Position();

		if (position.getY() == getPositionDepart().getX()) {
			possiblePosition.setX(position.getX());
			possiblePosition.setY(position.getY() + getPositionDepart().getY());
			if (ps.get(possiblePosition.coordinate()) != null)
				result.add(possiblePosition.coordinate());
		}
		return result;
	}




	protected Set<String> checkPieceBetween(Set<String> result, Positions ps, Position position, boolean doRock) {
		Set<String> result2 = new HashSet<>();

		for (String r : result) {
			boolean side = false;
			boolean isStray = false;
			;

			/**
			 * this case is specific for Pion as they do not eat always.
			 * 
			 */
			isStray = ps.get(r).getX() != position.getX();
			boolean isEmpty= ps.get(r).getPiece()== Piece.None;
			side = !isEmpty && ps.get(r).getPiece().getSide() != position.getPiece().getSide();
			if (side && isStray && !pieceBetweenOriginAndTarget(ps, position, ps.get(r))) {
				//allow eating stray target position:
				result2.add(r);
			}
			if (isEmpty && !isStray && !pieceBetweenOriginAndTarget(ps, position, ps.get(r))) {
				//allow moving forward if empty target position:
				result2.add(r);
			}
			
		}
		return result2;
	}
}
