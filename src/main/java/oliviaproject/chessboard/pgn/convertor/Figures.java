package oliviaproject.chessboard.pgn.convertor;

public class Figures extends AbstractConvertor implements IConvertor {

	public Figures() {
		convertorType=ConvertorType.Figures;
	}



	protected Trigger find(String value) {
		char c = value.charAt(0);
		Character.isUpperCase(c);
		return Character.isUpperCase(c)? Trigger.yes:Trigger.no;
	}

	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			result = value.substring(1);
			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}

}
