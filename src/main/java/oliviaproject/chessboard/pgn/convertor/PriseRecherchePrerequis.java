package oliviaproject.chessboard.pgn.convertor;

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
	char[] possibleChars=new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	char[] possibleDigits=new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};

	Prerequis prerequis;
public PriseRecherchePrerequis() {
	convertorType=ConvertorType.PriseRecherchePrerequis;

}


	protected Trigger find(String value) {
		if(value.length()<=2 || !value.contains("x"))return Trigger.no;
		char c0= value.charAt(0);
		char c1= value.charAt(1);
		char c2= value.charAt(2);
		boolean b0=isAH(c0);
		boolean b00=isDigit(c0);
		boolean b01=isDigit(c1);
		Trigger trigger;
		boolean b1=c1=='x';
		boolean b2=c2=='x';

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

	private boolean isDigit(char c0) {
		for(int c: possibleDigits) {
			if(c==c0) {
				return true;
			}
		}
		return false;	}


	private boolean isAH(char c0) {
		for(char c: possibleChars) {
			if(c==c0) {
				return true;
			}
		}
		return false;
	}


	String valueAfter() {
		String result;
		switch (trigger) {
		case no: {
			result = value;
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
