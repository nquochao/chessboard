package oliviaproject.chessboard.pgn.convertor;

public class Figures extends AbstractConvertor implements IConvertor {

	

	@Override
	public void load(String value, Boolean whiteToMoveConvertors) {
		trigger = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	Trigger find(String value) {
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
