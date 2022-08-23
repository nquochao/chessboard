package oliviaproject.chessboard.pgn.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConvertor implements IConvertor {
	protected static final Logger log = LoggerFactory.getLogger(AbstractConvertor.class);
	char[] possibleChars=new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	char[] possibleDigits=new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};
	char[] figures=new char[] {
			'B'//Fou
			,
			'K'//King
			,
			'O'//King pour roc
			,
			'N'//Fou
			,
			'Q'//dame, 'R'
			,
			'R'// Tour
	
	};
	protected Trigger trigger;
	protected String value, nextValue;
	ConvertorType convertorType;
	
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

		this.whiteToMove=whiteToMove;
		
	}	protected abstract Trigger find(String value2);

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

	protected boolean isDigit(char c0) {
		for(int c: possibleDigits) {
			if(c==c0) {
				return true;
			}
		}
		return false;	}

	protected boolean isFigure(char c0) {
		for(char c: figures) {
			if(c==c0) {
				return true;
			}
		}
		return false;
	}
	protected boolean isAH(char c0) {
		for(char c: possibleChars) {
			if(c==c0) {
				return true;
			}
		}
		return false;
	}

}
