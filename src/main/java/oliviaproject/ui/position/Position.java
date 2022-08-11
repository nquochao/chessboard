package oliviaproject.ui.position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import oliviaproject.ui.dashboard.OliviaPanel;
import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.possiblemove.PositionRockPossible;
import oliviaproject.ui.possiblemove.PositionUtil;

/**
 * @author DesktopPC
 *
 */
public class Position {

	int x, y;
	public static String separator = ",";
	Piece piece;
	protected Set<Position> possibleMovesEating = new HashSet<>();
	protected Set<Position> possibleMovesEatingEnPassant = new HashSet<>();
	/**
	 * this lastPosition is used for pion en passant move. as we need to check the
	 * immediate feature we also use a lastPosition on the board to check if the
	 * pion being eaten has advanced by 2 the previous strike
	 * 
	 */
	protected Position lastPosition;

	public Position getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Position lastPosition) {
		this.lastPosition = lastPosition;
	}

	/**
	 * For the King: the key is the position of the Tower. The value is the target
	 * position if we rock with the key Tower For the Tower: the key is the position
	 * of the King. The value is the target position if we rock with the key King
	 *
	 */
	protected Map<String, Position> possibleRock = new HashMap<>();
	protected Set<Position> possibleMoves = new HashSet<>();

	/**
	 * @author DesktopPC to verify whether the piece has already moved. this is
	 *         needed for King Rock with Tower It will be updated in the
	 *         {@link PieceColorPanel.mouseReleased}
	 *
	 */

	Boolean pieceHasMoved = false;

	public Set<Position> getPossibleMovesEating() {
		return possibleMovesEating;
	}

	public void setPossibleMovesEating(Set<Position> possibleMovesEating) {
		this.possibleMovesEating = possibleMovesEating;
	}

	public Map<String, Position> getPossibleRock() {
		return possibleRock;
	}

	public void setPossibleRock(Map<String, Position> possibleRock) {
		this.possibleRock = possibleRock;
	}

	public Set<Position> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(Set<Position> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	public Set<Position> getPossibleMovesEatingEnPassant() {
		return possibleMovesEatingEnPassant;
	}

	public void setPossibleMovesEatingEnPassant(Set<Position> possibleMovesEatingEnPassant) {
		this.possibleMovesEatingEnPassant = possibleMovesEatingEnPassant;
	}

	public Boolean getPieceHasMoved() {
		return pieceHasMoved;
	}

	public void setPieceHasMoved(Boolean pieceHasMoved) {
		this.pieceHasMoved = pieceHasMoved;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Position(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	public Position() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String coordinate() {
		return x + separator + y;
	}

	public Set<String> findPossibleMove(Positions ps, Position lastPositionMove) {
		return piece.getPossibleMove().getPossibleMove(ps, this, lastPositionMove);
	}

	protected Set<String> getPossibleMove(Positions ps, Position position, Position lastMove) {
		Set<String> result = PositionUtil.findPossibleMoves(possibleMoves, position, ps);
		Set<String> caseDeparts = caseDepart(position, ps);
		result.addAll(caseDeparts);
		result.addAll(PositionUtil.findPossibleMoveEating(possibleMovesEating, position, ps));
		Set<String> enPassant = PositionUtil.findPossibleMoveEatingEnPassant(possibleMovesEatingEnPassant, position,
				ps, lastMove);
		if (!getPieceHasMoved())
			result.addAll(getPossibleRock(ps, position));
		Set<String> result2 = new HashSet<>();
		result2 = checkPieceBetween(result, ps, position);
		result2.addAll(enPassant);
		/**
		 * the checkPieceBetween ofthe PositionPion would not allow for enPassant but
		 * the checks have been done in findPossibleMoveEatingEnPassant
		 */

		return result2;
	}

	/**
	 * This is implemented in Child class {@link Position}
	 * 
	 * @param position
	 * @param ps
	 * @return
	 */
	protected Set<String> caseDepart(Position position, Positions ps) {
		// TODO Auto-generated method stub
		return new HashSet<>();
	}

	/**
	 * This is implemented in Child class {@link PositionRockPossible}
	 * 
	 * @param ps
	 * @param position
	 * @return
	 */
	protected Set<String> getPossibleRock(Positions ps, Position position) {
		// TODO Auto-generated method stub
		return new HashSet<>();
	}

	protected boolean pieceBetweenOriginAndTarget(Positions ps, Position position, Position target) {
		int offsetX = target.getX() - position.getX();
		int offsetY = target.getY() - position.getY();
		int xpositive = offsetX > 0 ? 1 : -1;
		int ypositive = offsetY > 0 ? 1 : -1;
		Map<String, String> mypossiblePosition = new HashMap<>();
		if (offsetY == 0) {
			Position p = new Position(position.getX(), position.getY());
			for (int i = 1; i < offsetX; i++) {
				x = position.getX() + i;
				p.setX(x);
				mypossiblePosition.put(p.coordinate(), p.coordinate());

				if (ps.get(p.coordinate()) != null && ps.get(p.coordinate()).getPiece() != Piece.None)
					return true;
			}
			for (int i = 1; i < -offsetX; i++) {
				x = position.getX() - i;
				p.setX(x);
				mypossiblePosition.put(p.coordinate(), p.coordinate());

				if (ps.get(p.coordinate()) != null && ps.get(p.coordinate()).getPiece() != Piece.None)
					return true;

			}

		} else if (offsetX == 0) {
			Position p = new Position(position.getX(), position.getY());
			for (int i = 1; i < offsetY; i++) {
				y = position.getY() + i;
				p.setY(y);
				mypossiblePosition.put(p.coordinate(), p.coordinate());
				if (ps.get(p.coordinate()) != null && ps.get(p.coordinate()).getPiece() != Piece.None)
					return true;

			}
			for (int i = 1; i < -offsetY; i++) {
				y = position.getY() - i;
				p.setY(y);
				mypossiblePosition.put(p.coordinate(), p.coordinate());

				if (ps.get(p.coordinate()) != null && ps.get(p.coordinate()).getPiece() != Piece.None)
					return true;

			}

		} else {// diagonal
			Position p = new Position(position.getX(), position.getY());

			for (int i = 1; i < offsetX; i++) {
				x = position.getX() + xpositive * i;
				p.setX(x);
				y = position.getY() + ypositive * i;
				p.setY(y);
				mypossiblePosition.put(p.coordinate(), p.coordinate());
				if (ps.get(p.coordinate()) != null && ps.get(p.coordinate()).getPiece() != Piece.None)
					return true;

			}
			for (int i = 1; i < -offsetX; i++) {
				x = position.getX() + xpositive * i;
				p.setX(x);
				y = position.getY() + ypositive * i;
				p.setY(y);
				mypossiblePosition.put(p.coordinate(), p.coordinate());

				if (ps.get(p.coordinate()) != null && ps.get(p.coordinate()).getPiece() != Piece.None)
					return true;

			}

		}
		return false;
	}

	protected Set<String> checkPieceBetween(Set<String> result, Positions ps, Position position, boolean doRock) {
		Set<String> result2 = new HashSet<>();

		for (String r : result) {
			boolean side;
			if (!doRock) {
				/**
				 * this case allows eating opposite side if needed. This is good for all pieces
				 * except for Pion.
				 * 
				 */
				side = ps.get(r).getPiece().getSide() != position.getPiece().getSide();
			} else {
				/**
				 * this case allows for tower/king rock with same color. the target positions
				 * are then calculated afterward in the panel when we release the button
				 * 
				 */
				side = ps.get(r).getPiece().getSide() == position.getPiece().getSide();
			}

			if (side && !pieceBetweenOriginAndTarget(ps, position, ps.get(r))) {
				result2.add(r);
			}
		}
		return result2;
	}

	protected Set<String> checkPieceBetween(Set<String> result, Positions ps, Position position) {
		return checkPieceBetween(result, ps, position, false);
	}

	public void updatePosition(Position position) {
		this.setX(position.getX());
		this.setY(position.getY());
	}

	public void init(Position position) {
		this.setX(position.getX());
		this.setY(position.getY());
	}

}
