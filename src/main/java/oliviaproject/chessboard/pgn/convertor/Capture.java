package oliviaproject.chessboard.pgn.convertor;

public class Capture extends AbstractConvertor implements IConvertor {

	

	@Override
	public void load(String value, Boolean whiteToMoveConvertors) {
		trigger = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	Trigger find(String value) {
		char c = value.charAt(0);

		switch (c) {
		case 'x': {
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
