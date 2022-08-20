package oliviaproject.chessboard.pgn.convertor;

public class Standard extends AbstractConvertor implements IConvertor {

	

	@Override
	public void load(String value, Boolean whiteToMoveConvertors) {
		trigger = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	Trigger find(String value) {
		char c = value.charAt(0);
		Boolean b=Character.isLowerCase(c) &&value.length() == 2 
				&&!value.contains("x")&&!value.contains("o");
		return b? Trigger.yes:Trigger.no;
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
