package oliviaproject.chessboard.pgn.convertor;

public class Capture extends AbstractConvertor implements IConvertor {

	
public Capture() {
	convertorType=ConvertorType.Capture;
}


	protected Trigger find(String value) {
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

	/**
	 * returns the position of th eaten figure
	 */
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
