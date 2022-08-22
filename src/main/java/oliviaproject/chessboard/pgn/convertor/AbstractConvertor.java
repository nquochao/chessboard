package oliviaproject.chessboard.pgn.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConvertor implements IConvertor {
	protected static final Logger log = LoggerFactory.getLogger(AbstractConvertor.class);

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
	public abstract void load(String value, Boolean whiteToMove);
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

}
