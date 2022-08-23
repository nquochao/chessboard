
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class RocSmall extends AbstractConvertor implements IConvertor{
public RocSmall() {
	convertorType=ConvertorType.PetitRoc;

}
	Piece piece;


	protected Trigger find(String value) {
		char c = value.charAt(0);

		switch (value) {
		case "O-O": {
			return Trigger.yes;
		}
		default:{
			return Trigger.no;
		}
		}
	}




}
