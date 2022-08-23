package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.chessboard.pgn.PGNReader;

/**
 * @author HaoNguyen Si la première lettre est de a à h et la seconde un x ou
 *         une lettre de a à h Prérequis : la pièce doit être dans la colonne
 *         désignée par cette lettre On peut alors retirer la première lettre du
 *         mot. Sinon, si la première lettre est un chiffre de 1 à 8 et la
 *         seconde un x ou une lettre de a à h Prérequis : la pièce doit être
 *         dans la ligne désignée par ce chiffre On peut alors retirer la
 *         première lettre du mot. Sinon, si la première lettre est de a à h et
 *         si la seconde lettre est un chiffre de 1 à 8 et la troisième un x ou
 *         une lettre de a à h Prérequis : la pièce doit être dans la case
 *         désignée par la lettre et le chiffre On peut alors retirer la
 *         première et la seconde lettre du mot. Sinon, il n'y a pas de
 *         prérequis (donc pas d'ambiguité)
 */
public class PriseRecherchePrerequis extends AbstractConvertor implements IConvertor {

	Prerequis prerequis;
public PriseRecherchePrerequis() {
	convertorType=ConvertorType.PriseRecherchePrerequis;

}


	protected Trigger find(String value) {
		value=removeComments(value);

		if(value.length()<=2 || !isPrise(value))return Trigger.no;
		char c0= value.charAt(0);
		char c1= value.charAt(1);
		char c2= value.charAt(2);
		boolean b0=isAH(c0);
		boolean b00=isDigit(c0);
		boolean b01=isDigit(c1);
		Trigger trigger;
		boolean b1=c1==PRISE;
		boolean b2=c2==PRISE;

		if(b0&b1) {
			trigger=Trigger.yes;
			prerequis=Prerequis.line;
			
		}else if(b00 & b1){
			trigger=Trigger.yes;
			prerequis=Prerequis.column;
		}else if(b0 & b01 & b2){
			trigger=Trigger.yes;
			prerequis=Prerequis.both;
		}else {
			trigger=Trigger.no;
		}
			return trigger;
	}


	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			result = value.substring(value.indexOf(PRISE)+1);;
			result=PGNReader.findPosition(result, new Convertors());
			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}
	protected String valueBefore() {
		String result;
		switch (trigger) {
		case yes: {
			
			result = value.substring(0,value.indexOf(PRISE));;
			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}
	Prerequis findPrerequis(String v) {
		if (Character.isDigit(v.charAt(0)))
			return Prerequis.line;
		else
			return Prerequis.column;
	}

}
