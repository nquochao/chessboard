
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class RocBig extends AbstractConvertor implements IConvertor{
public RocBig() {
	convertorType=ConvertorType.GrandRoc;

}
	Piece piece;


	protected Trigger find(String value) {
		char c = value.charAt(0);

		switch (value) {
		case "O-O-O": {
			return Trigger.yes;
		}
		default:{
			return Trigger.no;
		}
		}
	}




}
