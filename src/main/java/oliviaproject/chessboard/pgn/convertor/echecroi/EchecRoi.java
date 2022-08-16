
package oliviaproject.chessboard.pgn.convertor.echecroi;

import oliviaproject.chessboard.pgn.convertor.capture.CaptureType;
import oliviaproject.ui.dashboard.util.Piece;

public class EchecRoi {

	EchecRoiType captureType;
	String value, nextValue;
	Piece piece;
	Boolean whiteToMove;

	public void load(String value, Boolean whiteToMove) {
		captureType = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	EchecRoiType find(String value) {
		char c = value.charAt(0);

		switch (c) {
		case '+': {
			return EchecRoiType.yes;
		}
		default:{
			return EchecRoiType.no;
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
