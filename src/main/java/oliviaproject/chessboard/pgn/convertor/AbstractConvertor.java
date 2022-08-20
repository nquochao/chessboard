package oliviaproject.chessboard.pgn.convertor;

public class AbstractConvertor implements IConvertor {

	protected Trigger trigger;
	protected String value, nextValue;
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
		// TODO Auto-generated method stub
		
	};

}
