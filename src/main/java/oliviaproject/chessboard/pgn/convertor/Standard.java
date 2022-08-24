package oliviaproject.chessboard.pgn.convertor;

public class Standard extends AbstractConvertor implements IConvertor {

	public Standard() {
		convertorType=ConvertorType.Standard;
	}



	protected Trigger find(String value) {
		value=removeComments(value);

		char c = value.charAt(0);
		Boolean b=Character.isLowerCase(c) &&value.length() == 2 
				&&!isPrise(c);
		return b? Trigger.yes:Trigger.no;
	}

	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			result=toCoordinate.findCoordinate(value);
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
			result="";
			break;
		}
		default: {
			result = "";
		}
		}
		return result;
	}
}
