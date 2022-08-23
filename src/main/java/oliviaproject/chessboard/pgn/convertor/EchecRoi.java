
package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.ui.dashboard.util.Piece;

public class EchecRoi extends AbstractConvertor implements IConvertor{
public EchecRoi() {
	convertorType=ConvertorType.EchecRoi;

}
	Piece piece;


	protected Trigger find(String value) {
		char c = value.charAt(0);
		//++	Indique un échec et mat selon le règlement de la FIDE.
		//Certains auteurs l'utilisent cependant pour marquer un échec double.
		switch (c) {

		case '+': {
			//echec et mat
			return Trigger.yes;
		}
		default:{
			return Trigger.no;
		}
		}
	}




}
