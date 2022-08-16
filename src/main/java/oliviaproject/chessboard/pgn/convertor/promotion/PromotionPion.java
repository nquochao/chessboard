package oliviaproject.chessboard.pgn.convertor.promotion;

import oliviaproject.chessboard.pgn.convertor.capture.CaptureType;
import oliviaproject.ui.dashboard.util.Piece;

public class PromotionPion {
	String value, nextValue;
	Piece piece;
	Boolean whiteToMove;
	PromotionPionType promotionPionType;

	public void load(String value, Boolean whiteToMove) {
		this.value = value;
		nextValue = valueAfter();
		this.whiteToMove=whiteToMove;
	}

	PromotionPionType find(String value) {
		char c = value.charAt(0);

		switch (c) {
		case '=': {
			return PromotionPionType.yes;
		}
		default:{
			return PromotionPionType.no;
		}
		}
	}

	String valueAfter() {
		String result;
		switch (promotionPionType) {
		case yes: {
			result = value.substring(2);
			this.piece=Piece.determinePiece(value, whiteToMove);

			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}

}
