
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class RocSmall extends AbstractConvertor implements IConvertor{
public RocSmall() {
	convertorType=ConvertorType.PetitRoc;

}
@Override
	public void load(String value, Boolean whiteToMove) {
		// TODO Auto-generated method stub
		super.load(value, whiteToMove);

		
}

	protected Trigger find(String value) {
		value=removeComments(value);

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
	@Override
		String valueAfter() {
		String result= new String();
		switch (trigger) {
			case yes:{
				result=getWhiteToMove()?"6,7":"6,0";
				break;
			}
			}
		return result;
		}

}
