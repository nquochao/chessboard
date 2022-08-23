
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class EchecEtMat extends AbstractConvertor implements IConvertor{
	
	public EchecEtMat() {
	convertorType=ConvertorType.EchecEtMat;

}
	Piece piece;


	protected Trigger find(String value) {
		value=removeComments(value);

		char c = value.charAt(0);

		switch (c) {
		case '+': {
			if(value.equals("++")) return Trigger.yes;
		}
		case '#':{
			//echec au roi
			return Trigger.yes;
		}
		default:{
			return Trigger.no;
		}
		}
	}



}
