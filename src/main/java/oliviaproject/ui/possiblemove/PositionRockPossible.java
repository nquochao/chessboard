package oliviaproject.ui.possiblemove;

import java.util.HashSet;
import java.util.Set;

import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;

public abstract class PositionRockPossible extends Position {
	public abstract Position getPositionDepart();


	public PositionRockPossible() {
		super();// TODO Auto-generated constructor stub
	}

	public PositionRockPossible(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * RockPossible is the mother class of {@link PositionRoi} and
	 * {@link PositionTower}so we need to treat the 2 cases in the child class thus
	 * the function is abstract
	 * 
	 * @param ps
	 * @param position
	 * @return
	 */
	/**
	 * we retrieve the 2 towers. we do a/ check that it has not moved with
	 * {@link PositionTour} b/ add them as possible target position if a/ Note: we
	 * need to checkPieceBetween as we need to change the target X position as it is
	 * not the position of the tower but intermediate position
	 */
	protected Set<String> getPossibleRock(Positions ps, Position position) {
		
		Set<String> result = new HashSet<>();
		for (String coordinate : possibleRock.keySet()) {
			
			Position tower = ps.get(coordinate);
			if (!tower.getPieceHasMoved())
				result.add(coordinate);
			
		}
		Boolean doRock=true;
		Set<String> result2 = checkPieceBetween(result, ps, position, doRock);
		Set<String> result3 = new HashSet<>();
		for(String coordinateTower: result2) {
			Position targetKingPosition= possibleRock.get(coordinateTower);
			String coordinate=targetKingPosition.coordinate();
			result3.add(coordinate);
		}
		return result3;
	}

}
