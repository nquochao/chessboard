package oliviaproject.ui.dashboard;

import java.util.HashSet;
import java.util.Set;

import oliviaproject.chessboard.pgn.Move;
import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.dashboard.util.PlayMode;
import oliviaproject.ui.dashboard.util.Side;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;
import oliviaproject.ui.possiblemove.KingSide;
import oliviaproject.ui.possiblemove.PositionUtil;
import oliviaproject.ui.possiblemove.Revert;

public class MoveAction {
public Boolean move(String coordinate, Set<String> possiblepositions,Positions ps ,Position pOrigin, Position lastPosition) {
	Position pTarget = ps.get(coordinate);
//	if(pTarget.coordinate().equals(p.coordinate())){
//		return;// this is not necessary but in case possiblepositions not well calculated
//	}

	if (possiblepositions != null && possiblepositions.contains(pTarget.coordinate())) {
		;
		PositionUtil.doRock(pTarget, pOrigin, ps, Revert.DoRegular);
		PositionUtil.doEnPassant(pTarget, pOrigin, ps, lastPosition, Revert.DoRegular);

		ps.get(pTarget.coordinate()).setPiece(pOrigin.getPiece());
		PositionUtil.updatePiece(pTarget, ps, Revert.DoRegular);
		lastPosition.updatePosition(pTarget, Revert.DoRegular);

		// we check if it is a rock move

		ps.get(pOrigin.coordinate()).setPiece(Piece.None);
		PositionUtil.updatePiece(pOrigin, ps, Revert.DoRegular);
		PositionUtil.doPromotion(pTarget, ps, Revert.DoRegular);
		/**
		 * is the move putting my King in chess?
		 */
		boolean isMyKingInChess = PositionUtil.checkMate(pTarget, lastPosition, ps,
				KingSide.MyKing);

		if (isMyKingInChess) {
			/**
			 * Compulsory : we cannot accept the move and need to revert.
			;

			PositionUtil.doRock(pTarget, pOrigin, ps, Revert.DoRevert);
			PositionUtil.doEnPassant(pTarget, pOrigin, ps, lastPosition, Revert.DoRevert);
			// do the revert of this move:
			ps.get(pTarget.coordinate()).setPiece(pOrigin.getPiece());
			PositionUtil.updatePiece(pTarget, ps, Revert.DoRevert);
			lastPosition.updatePosition(pTarget, Revert.DoRevert);

			// we check if it is a rock move

			ps.get(pOrigin.coordinate()).setPiece(Piece.None);
			PositionUtil.updatePiece(pOrigin, ps, Revert.DoRevert);
			PositionUtil.doPromotion(pTarget, ps, Revert.DoRevert);
			return false;
			 */

		}
		/**
		 * is the move putting the opposite King in chess?
		 */
		boolean isOppositeKingInChess = PositionUtil.checkMate(pTarget, lastPosition, ps,
				KingSide.OppositeKing);
		if (isOppositeKingInChess) {
			/**
			 * Optional notification
			 * 
			 */
		}

	}
	return true;
}
 void updateNextPlayer(PlayMode playMode, String key, Positions ps) {
		Position selectedPosition = ps.get(key);
		if (playMode != PlayMode.game) return;

		if (selectedPosition != null && selectedPosition.getPiece() != Piece.None) {
			int nextvalue = (playMode.getSideToPlay().ordinal() + 1) % Side.values().length;
			Side s = Side.values()[nextvalue];
			if (s == Side.None)
				nextvalue = (nextvalue + 1) % Side.values().length;
			s = Side.values()[nextvalue];
			playMode.setSideToPlay(s);
		}
	}
/**
 * The move should provide enough info to calculate the initial Origin Position.
 * @param move
 * @param ps
 * @return
 */
public Position calculatePossibleOriginPositions(Move move, Positions ps) {
	Positions result=new Positions();
	/**
	 *  first check move.getPiece()+ getWhiteToPlay
	 */
	Piece piece= move.getPiece();
	Side s=PositionUtil.convert(move.getWhiteToPlay());
	result=PositionUtil.findPositions(piece, ps);
	result=PositionUtil.findPositions(s, result);
	/**
	 *  2nd check which moves are possible to go to moveTo
	 */
	Set<Position> result2=new HashSet<>();
	for(String key: result.keySet()) {
		Position p=result.get(key);
		Position lastPosition=null; 
		/**
		 * lastPositioin is only used for enPassant. I added the case whe lastPosition is null to return nothing when doing enpassant
		 * this is to simplify the pgn reader (assumption taken).
		 * 
		 */
		String moveTo= move.getTo();
		Set<String> possiblepositions = p.findPossibleMove(ps, lastPosition);
		for(String coordinate : possiblepositions) {
			if(coordinate.equals(moveTo)) {
				result2.add(p);
			}
		}
	}
	String moveFrom= move.getFrom();
	Set<Position> result3=new HashSet<>();
	for(Position p: result2) {
		Positions positions=Position.getPossibleValues(moveFrom, ps);
		for(String c: positions.keySet()) {
				if(p.coordinate().equals(c)){
					result3.add(p);
				}
		}
	}
	/**
	 *  3rd check which moves are possible to come from moveFrom
	 */

// there should be one only...
	return result.get(0);
}

}
