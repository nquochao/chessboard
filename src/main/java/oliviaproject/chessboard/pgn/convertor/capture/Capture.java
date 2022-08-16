package oliviaproject.chessboard.pgn.convertor.capture;

import oliviaproject.chessboard.pgn.convertor.prerequis.Prerequis;

public class Capture {

	CaptureType captureType;
	String value, nextValue;

	public void load(String value) {
		captureType = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	CaptureType find(String value) {
		char c = value.charAt(0);

		switch (c) {
		case 'x': {
			return CaptureType.yes;
		}
		default:{
			return CaptureType.no;
		}
		}
	}

	String valueAfter() {
		String result;
		switch (captureType) {
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
