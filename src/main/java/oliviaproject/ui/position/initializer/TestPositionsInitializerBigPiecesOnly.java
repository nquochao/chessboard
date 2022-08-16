package oliviaproject.ui.position.initializer;

import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.dashboard.util.Side;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;

public class TestPositionsInitializerBigPiecesOnly implements IPositionsInitializer {
	private Positions positions;

	public TestPositionsInitializerBigPiecesOnly() {
		super();
	}
	public TestPositionsInitializerBigPiecesOnly(Positions positions) {
		super();
		this.positions=positions;
	}
	/**
	 * @param s
	 */
	public void initialize(Side s) {
		switch (s) {
		case White: {

			initializeWhite();
			break;
		}
		case Black: {
			initializeBlack();
			break;
		}
		}
		initializePossibleMoves();
	}
	/**
	 * Rock possibilities and eating /possible moves are triggered once we know the piece/positioning 
	 */
	protected void initializePossibleMoves() {
		for (String position : positions.keySet())
		{
			
			Position p = positions.get(position);

			p.getPiece().getPossibleMove().init(p);
			
			
		}
		
	}
	protected void initializeBlack() {
		for (String position : positions.keySet()) {
			Position p = positions.get(position);
			switch (p.getY()) {
			case 0: {
				initBigPiece(p, Piece.PionW);
				break;
			}
			case 7: {
				initBigPiece(p, Piece.PionB);
				break;
			}
			default:{
				p.setPiece(Piece.None);
				break;
			}
			}
		}

	}

	protected void initBigPiece(Position p, Piece pionw) {

		switch (pionw) {
		case PionB: {
			switch (p.getX()) {
			case 0: {
				p.setPiece(Piece.TourB);
				break;
			}
			case 1: {
				p.setPiece(Piece.CavalierB);
				break;
			}
			case 2: {
				p.setPiece(Piece.FouB);
				break;
			}
			case 3: {
				p.setPiece(Piece.DameB);
				break;
			}
			case 4: {
				p.setPiece(Piece.RoiB);
				break;
			}
			case 7: {
				p.setPiece(Piece.TourB);
				break;
			}
			case 6: {
				p.setPiece(Piece.CavalierB);
				break;
			}
			case 5: {
				p.setPiece(Piece.FouB);
				break;
			}		default:{
				p.setPiece(Piece.None);
			}
			}
			break;
		}
		case PionW: {
			switch (p.getX()) {
			case 0: {
				p.setPiece(Piece.TourW);
				break;
			}
			case 1: {
				p.setPiece(Piece.CavalierW);
				break;
			}
			case 2: {
				p.setPiece(Piece.FouW);
				break;
			}
			case 3: {
				p.setPiece(Piece.DameW);
				break;
			}
			case 4: {
				p.setPiece(Piece.RoiW);
				break;
			}
			case 7: {
				p.setPiece(Piece.TourW);
				break;
			}
			case 6: {
				p.setPiece(Piece.CavalierW);
				break;
			}
			case 5: {
				p.setPiece(Piece.FouW);
				break;
			}
			}

		}
		break;
		}

	}

	protected void initializeWhite() {
		for (String position : positions.keySet()) {
			Position p = positions.get(position);
			switch (p.getY()) {
			case 0: {
				initBigPiece(p, Piece.PionB);
				break;

			}

			case 7: {
				initBigPiece(p, Piece.PionW);
				break;
			}			default:{
				p.setPiece(Piece.None);
			}
			}
		}

	}
}
