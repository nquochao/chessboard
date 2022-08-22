
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class EchecRoi extends AbstractConvertor implements IConvertor{
public EchecRoi() {
	convertorType=ConvertorType.EchecRoi;

}
	Piece piece;

	public void load(String value, Boolean whiteToMove) {
		trigger = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	Trigger find(String value) {
		char c = value.charAt(0);

		switch (c) {
		case 'O': {
			return Trigger.yes;
		}
		default:{
			return Trigger.no;
		}
		}
	}




}
