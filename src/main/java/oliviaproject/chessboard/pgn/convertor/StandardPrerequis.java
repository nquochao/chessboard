package oliviaproject.chessboard.pgn.convertor;

import oliviaproject.chessboard.pgn.PGNReader;

public class StandardPrerequis extends AbstractConvertor implements IConvertor {

	public StandardPrerequis() {
		convertorType=ConvertorType.StandardPrerequis;
	}



	protected Trigger find(String value) {
		value=removeComments(value);
		log.info(value);
		char c0 = value.charAt(0);
		Boolean b=isAH(c0)|isDigit(c0) &&value.length() > 2 
				&&!isPrise(value);
		return b? Trigger.yes:Trigger.no;
	}

	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			
			result=value.substring(1);
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
			result = value.substring(0,1);;
			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}

}
