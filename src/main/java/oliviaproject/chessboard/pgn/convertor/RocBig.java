
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class RocBig extends AbstractConvertor implements IConvertor{
public RocBig() {
	convertorType=ConvertorType.GrandRoc;

}
	Piece piece;


	protected Trigger find(String value) {
		value=removeComments(value);

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
	@Override
	String valueAfter() {
	String result= new String();
	switch (trigger) {
		case yes:{
			result=getWhiteToMove()?"2,7":"2,0";
			break;
		}
		}
	return result;
	}




}
