package oliviaproject.ui.possiblemove;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.dashboard.util.Side;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;
import oliviaproject.ui.promotion.PromotionPanel;

public class PositionUtil {
	public static boolean checkIfPieceAdverse(Position pieceadverse, Position myPosition, Positions ps) {
		pieceadverse = ps.get(pieceadverse.coordinate());
		return (pieceadverse != null && pieceadverse.getPiece().getSide() != myPosition.getPiece().getSide()
				&& pieceadverse.getPiece() != Piece.None);

	}

	public static boolean checkIfPieceEmpty(Position position, Positions ps) {
		position = ps.get(position.coordinate());
		return (position != null && position.getPiece() == Piece.None);

	}

	public static Set<String> findPossibleMoveEating(Set<Position> possibleMovesEating, Position position,
			Positions ps) {
		Set<String> result = new HashSet<>();

		for (Position offset : possibleMovesEating) {
			Position pieceadverse = new Position();
			pieceadverse.setX(position.getX() + offset.getX());
			pieceadverse.setY(position.getY() + offset.getY());
			boolean isAdverse = checkIfPieceAdverse(pieceadverse, position, ps);
			if (isAdverse)
				result.add(pieceadverse.coordinate());
		}
		return result;
	}

	public static Set<String> findPossibleMoves(Set<Position> possibleMoves, Position position, Positions ps) {

		Set<String> result = new HashSet<>();
		Position possiblePosition = new Position();
		for (Position offset : possibleMoves) {
			possiblePosition.setX(position.getX() + offset.getX());
			possiblePosition.setY(position.getY() + offset.getY());
			;
			if (ps.get(possiblePosition.coordinate()) != null)
				result.add(possiblePosition.coordinate());
		}
		return result;
	}

	public static Set<String> findPossibleMoveEatingEnPassant(Set<Position> possibleMovesEatingEnPassant,
			Position position, Positions ps, Position lastPositionMove) {
		Set<String> result = new HashSet<>();
		Position possiblePosition = new Position();
		if (possibleMovesEatingEnPassant.size() == 0) {
			return result;
		}
		for (Position absolutePosition : possibleMovesEatingEnPassant) {
			possiblePosition.setX(position.getX() + absolutePosition.getX());
			possiblePosition.setY(absolutePosition.getY());
			boolean isEmpty = checkIfPieceEmpty(possiblePosition, ps);
			boolean enPassantAdversePosition = checkIfPieceAdverseEligibleForEnPassant(possiblePosition, position, ps,
					lastPositionMove);
			if (isEmpty && enPassantAdversePosition) {
				result.add(possiblePosition.coordinate());
			}
		}
		return result;

	}

	/**
	 * returns if adverse Pion + has used the init +2
	 * 
	 * @param position
	 * @param possiblePosition
	 * @param ps
	 * @return
	 */
	public static boolean checkIfPieceAdverseEligibleForEnPassant(Position possiblePosition, Position myposition,
			Positions ps, Position lastPositionMove) {
		PositionPion adversePosition = findAdverseEnPassantForAdvLastPosition(possiblePosition, myposition, ps);
		if (adversePosition == null)
			return false;
		Position lastPosition = adversePosition.getLastPosition();
		if (lastPosition == null)
			return false;
		if (lastPositionMove == null)
			return false;
		boolean isEligibleEnPassant = lastPosition.getY() == adversePosition.getPositionDepart().getY();
		Position advPosition = findAdverseEnPassant(possiblePosition, myposition, ps);
		boolean isEligibleEnPassantImmediate = false;
		isEligibleEnPassantImmediate = advPosition.coordinate().equals(lastPositionMove.coordinate());
		return checkIfPieceAdverse(adversePosition, myposition, ps) && isEligibleEnPassant
				&& isEligibleEnPassantImmediate;

	}

	public static Position findAdverseEnPassant(Position possiblePosition, Position myposition, Positions ps) {
		int offset = possiblePosition.getY() - myposition.getY();
		Position adversePosition = new Position(possiblePosition.getX(), possiblePosition.getY() - offset);
		if (ps.get(adversePosition.coordinate()) == null)
			return null;
		Piece adversePiece = ps.get(adversePosition.coordinate()).getPiece();
		boolean isPion = adversePiece == Piece.PionB || adversePiece == Piece.PionW;
		if (isPion) {
			return adversePosition;
		}
		return null;
	}

	public static PositionPion findAdverseEnPassantForAdvLastPosition(Position possiblePosition, Position myposition,
			Positions ps) {
		int offset = possiblePosition.getY() - myposition.getY();
		Position adversePosition = new Position(possiblePosition.getX(), possiblePosition.getY() - offset);
		if (ps.get(adversePosition.coordinate()) == null)
			return null;
		Piece adversePiece = ps.get(adversePosition.coordinate()).getPiece();
		boolean isPion = adversePiece == Piece.PionB || adversePiece == Piece.PionW;
		if (isPion) {
			return (PositionPion) adversePiece.getPossibleMove();
		}
		return null;
	}

	public static void updatePiece(Position pTarget, Positions ps, Revert dorevert) {
		Position p = ps.get(pTarget.coordinate()).getPiece().getPossibleMove();
		p.setPieceHasMoved(true);
		p.setLastPosition(new Position(p.getX(), p.getY()));
		p.updatePosition(pTarget, dorevert);

	}

	public static Boolean doRock(Position pTarget, Position p, Positions ps, Revert doRevert) {
		Map<String, Position> mypossibleMove = p.getPiece().getPossibleMove().getPossibleRock();
		Boolean isPossibleRock = false;
		String towerCoordinate = null;
		for (String coordinate : mypossibleMove.keySet()) {
			Position target = mypossibleMove.get(coordinate);
			if (target.coordinate().equals(pTarget.coordinate())) {
				isPossibleRock = true;
				towerCoordinate = coordinate;
				break;
			}
		}

		Boolean hasNotMoved = ps.get(p.coordinate()).getPiece().getPossibleMove().getPieceHasMoved() == false;
		if (isPossibleRock && hasNotMoved) {
			/**
			 * we move the tower
			 * 
			 */
			Position towerPosition = ps.get(towerCoordinate);
			Map<String, Position> possibleRocks = towerPosition.getPiece().getPossibleMove().getPossibleRock();
			Position targetTowerPosition = possibleRocks.get(p.coordinate());
			targetTowerPosition = ps.get(targetTowerPosition.coordinate());
			targetTowerPosition.setPiece(towerPosition.getPiece());
			PositionUtil.updatePiece(targetTowerPosition, ps, doRevert);

			towerPosition.setPiece(Piece.None);
			PositionUtil.updatePiece(towerPosition, ps, doRevert);

		}
		return isPossibleRock && hasNotMoved;

	}

	public static Boolean doEnPassant(Position possiblePosition, Position position, Positions ps,
			Position lastPositionMove, Revert dorevert) {
		boolean isEmpty = checkIfPieceEmpty(possiblePosition, ps);
		boolean enPassantAdversePosition = checkIfPieceAdverseEligibleForEnPassant(possiblePosition, position, ps,
				lastPositionMove);
		Boolean doEnPassant = isEmpty && enPassantAdversePosition;
		if (isEmpty && enPassantAdversePosition) {
			int offset = possiblePosition.getY() - position.getY();
			Position adversePosition = new Position(possiblePosition.getX(), possiblePosition.getY() - offset);

			Position advPosition = ps.get(adversePosition.coordinate());
			advPosition.setPiece(Piece.None);
			PositionUtil.updatePiece(advPosition, ps, dorevert);

		}

		return doEnPassant;

	}

	public static void doPromotion(Position pTarget, Positions ps, Revert doregular) {
		boolean isPion = (pTarget.getPiece() == Piece.PionB || pTarget.getPiece() == Piece.PionW);
		boolean isLastRow = pTarget.getY() == 0 || pTarget.getY() == 7;
		if (isPion && isLastRow) {
			PromotionPanel.showFrame(pTarget);
		}
	}

	public static boolean checkMate(Position pTarget, Position lastPosition, Positions ps, KingSide kingSide) {
		Position positionKing = null;
		Side s =findSide(pTarget.getPiece().getSide(),kingSide);
		
		/**
		 * Find position of the Kind of side s
		 * 
		 */
		if (s != Side.None) {
			positionKing = findKing(s, ps);
		}else return false;

		/**
		 * Get all the possible moves of the opposite side of s
		 * 
		 */
		Set<String> possiblePositions=new HashSet<>();
		Side oppositeSide = Side.getOppositeSide(s);
		for (String key : ps.keySet()) {

			Position p = ps.get(key);
			boolean isSide = oppositeSide == p.getPiece().getSide();
			if (isSide) {
				Set<String> possible = p.findPossibleMove(ps, lastPosition);
				possiblePositions.addAll(possible);
			}

		}
		/**
		 * Verify that the {King of side s} is not in the {possible moves of opposite
		 * side s}
		 * 
		 */
		boolean isKingChess=possiblePositions.contains(positionKing.coordinate());
		return isKingChess;
	

	}

	private static Side findSide(Side side, KingSide kingSide) {
		Side s = null;
		switch(kingSide) {
		case MyKing:{
			s = side;
			break;
		}
		case OppositeKing:{
			switch( side) {
			case White:{
				s= Side.Black;
				break;
			}
			case Black:{
				s=Side.White;
				break;
			}
			default:{
				s=Side.None;
				break;
			}
			};
			break;
		}
		}
		return s;
	}

	private static Position findKing(Side s, Positions ps) {
		Position positionKing = null;
		switch (s) {
		case White: {
			positionKing = findPiece(Piece.RoiW, ps);
			break;
		}
		case Black: {
			positionKing = findPiece(Piece.RoiB, ps);
			break;
		}
		}
		return positionKing;
	}

	/**
	 * we take the first found. This would work with RoiW or RoiB
	 * @param piece
	 * @param ps
	 * @return
	 */
	private static Position findPiece(Piece piece, Positions ps) {
		Positions result= findPositions(piece, ps);
		for (String key : result.keySet()) {
			Position p = ps.get(key);
			boolean isFound = p.getPiece() == piece;
			if (isFound) {
				return p;
			}

		}
		return null;
	}
	/**
	 * @param piece
	 * @param ps
	 * @return
	 */
	public static Positions findPositions(Piece piece, Positions ps) {
		Positions result=new Positions();
		for (String key : ps.keySet()) {
			Position p = ps.get(key);
			boolean isFound = p.getPiece() == piece;
			if(isFound)result.put(p.coordinate(),p);

		}
		return result;
	}
	/**
	 * @param piece
	 * @param ps
	 * @return
	 */
	public static Positions findPositions(Side side, Positions ps) {
		Positions result=new Positions();
		for (String key : ps.keySet()) {
			Position p = ps.get(key);
			boolean isFound = p.getPiece().getSide() == side;
			if(isFound)result.put(p.coordinate(),p);

		}
		return result;
	}
	public static Side convert(Boolean whiteToPlay) {
		if(whiteToPlay)return Side.White;
		else return Side.Black;
	}
}
