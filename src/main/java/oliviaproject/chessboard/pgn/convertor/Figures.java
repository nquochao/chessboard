package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.chessboard.pgn.PGNReader;

public class Figures extends AbstractConvertor implements IConvertor {

	public Figures() {
		convertorType=ConvertorType.Figures;
	}



	protected Trigger find(String value) {
		value=removeComments(value);

		char c = value.charAt(0);
		Character.isUpperCase(c);
		return isFigure(c)? Trigger.yes:Trigger.no;
	}

	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			result = value.substring(1);
			result=PGNReader.findPositionTo(result, new Convertors(), whiteToMove);
			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}

}
