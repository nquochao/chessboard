package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class PromotionPion extends AbstractConvertor  implements IConvertor{
	Piece piece;
public PromotionPion() {
this.convertorType=ConvertorType.PromotionPion;
}


	protected Trigger find(String value) {
		value=removeComments(value);

		char c = value.charAt(0);

		switch (c) {
		case '=': {
			return Trigger.yes;
		}
		default:{
			return Trigger.no;
		}
		}
	}

	String valueAfter() {
		String result;
		switch (trigger) {
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

	protected String valueBefore() {
		String result;
		switch (trigger) {
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
