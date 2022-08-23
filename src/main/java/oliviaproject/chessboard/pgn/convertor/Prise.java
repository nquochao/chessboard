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
public class Prise extends AbstractConvertor implements IConvertor {
	char[] possibleChars=new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	char[] possibleDigits=new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};

	Prerequis prerequis;
public Prise() {
	convertorType=ConvertorType.PriseRecherchePrerequis;

}


	protected Trigger find(String value) {
		if(value.length()<=2 || !value.contains("x"))return Trigger.no;
		char c0= value.charAt(0);
		char c1= value.charAt(1);
		char c2= value.charAt(2);

		Trigger trigger;
		boolean b0=c0=='x';

		if(b0) {
			trigger=Trigger.yes;
		}

			return Trigger.no;
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
