package oliviaproject.chessboard.pgn.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.convertor.coordinate.FromCoordinate;
import oliviaproject.chessboard.pgn.convertor.coordinate.ToCoordinate;

public abstract class AbstractConvertor implements IConvertor {
	static final char PRISE = 'x';
	protected static final Logger log = LoggerFactory.getLogger(AbstractConvertor.class);
	char[] possibleChars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
	char[] possibleDigits = new char[] { '1', '2', '3', '4', '5', '6', '7', '8' };
	char[] figures = new char[] { 'B'// Fou
			, 'K'// King
			,
//			'O'
			/**
			 * 
			 * King pour roc removed for {link Figures}
			 */
//			,
			'N'// Fou
			, 'Q'// dame, 'R'
			, 'R'// Tour

	};
	char [] comments=new char[] {
			'!', '?', '='
	};
	/**
	 * 
	 * 
!	Bon coup
!!	Très bon coup
?	Mauvais coup
??	Très mauvais coup
!?	Coup intéressant
?!	Coup douteux
+	Echec
++	Echec et mat
#	Echec et mat
x	Prise
e.p.	Prise en passant
o-o	Petit roque
o-o-o	Grand roque
=	Position égale
	Position indéterminée
	Les blancs sont gagnants
	Les blancs sont mieux
	Les blancs sont légèrement mieux
	Les noirs sont gagnants
	Les noirs sont mieux
	Les noirs sont légèrement mieux
1-0	Victoire des blancs
0-1	Victoire des noirs
0,5-0,5	Match nul
1/2-1/2	Match nul
	 * 
	 */
	ToCoordinate toCoordinate;
	FromCoordinate fromCoordinate;
	protected Trigger trigger;
	protected String value, nextValue, beforeValue;

	ConvertorType convertorType;

	public AbstractConvertor() {
		toCoordinate = new ToCoordinate();
		fromCoordinate = new FromCoordinate();
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public ConvertorType getConvertorType() {
		return convertorType;
	}

	public void setConvertorType(ConvertorType convertorType) {
		this.convertorType = convertorType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNextValue() {
		return nextValue;
	}

	public void setNextValue(String nextValue) {
		this.nextValue = nextValue;
	}

	public Boolean getWhiteToMove() {
		return whiteToMove;
	}

	public void setWhiteToMove(Boolean whiteToMove) {
		this.whiteToMove = whiteToMove;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	protected Boolean whiteToMove;

	public Trigger getTrigger() {
		return trigger;
	}

	@Override
	public void load(String value, Boolean whiteToMove) {
		this.value = value;
		trigger = find(value);
		nextValue = valueAfter();
		beforeValue = valueBefore();

		this.whiteToMove = whiteToMove;
		this.toCoordinate = new ToCoordinate();
		this.fromCoordinate = new FromCoordinate();

	}


	String valueBefore() {
		String result;
		switch (trigger) {
		case yes: {
			result = "";

			break;
		}
		default: {
			result = "";
		}
		}
		return result;
	}
	protected abstract Trigger find(String value2);

	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			result = convertorType.name();

			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}

	protected boolean isPrise(char c) {
		return (c == PRISE);
	}
	protected boolean isPrise(String s) {
		return (s.contains("x"));
	}
	protected boolean isDigit(char c0) {
		for (int c : possibleDigits) {
			if (c == c0) {
				return true;
			}
		}
		return false;
	}

	protected boolean isFigure(char c0) {
		for (char c : figures) {
			if (c == c0) {
				return true;
			}
		}
		return false;
	}

	protected boolean isAH(char c0) {
		for (char c : possibleChars) {
			if (c == c0) {
				return true;
			}
		}
		return false;
	}
	protected String removeComments(String value) {
		StringBuilder b=new StringBuilder();
		b.append('[');
		for(char c: comments) {
			b.append(c);
		}
		b.append(']');
		return value.split(b.toString())[0];

	}

}
