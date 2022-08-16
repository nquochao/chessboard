package oliviaproject.chessboard.pgn.convertor.prerequis;

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
public class RecherchePrerequis {

	Prerequis prerequis;
	String value, nextValue;

	public void load(String value) {
		prerequis = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	Prerequis find(String value) {
		String[] values = value.split("x");
		String v = values[0];
		switch (v.length()) {
		case 0: {
			return Prerequis.none;
		}
		case 1: {
			if (Character.isDigit(v.charAt(0)))
				return Prerequis.line;
			else
				return Prerequis.column;
		}
		case 2: {
			return Prerequis.both;
		}
		}
		return Prerequis.none;
	}

	String valueAfter() {
		String result;
		switch (prerequis) {
		case none: {
			result = value;
			break;
		}
		default: {
			result = value.split("x")[1];
		}
		}
		return result;
	}
}
