package oliviaproject.chessboard.pgn.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.PGNReader;

public class Standard extends AbstractConvertor implements IConvertor {

	public Standard() {
		convertorType=ConvertorType.Standard;
	}

	@Override
	public void load(String value, Boolean whiteToMoveConvertors) {
		trigger = find(value);
		this.value = value;
		nextValue = valueAfter();
	}

	Trigger find(String value) {
		char c = value.charAt(0);
		Boolean b=Character.isLowerCase(c) &&value.length() == 2 
				&&!value.contains("x")&&!value.contains("o");
		return b? Trigger.yes:Trigger.no;
	}

	String valueAfter() {
		String result;
		switch (trigger) {
		case yes: {
			result=findCoordinate(value);
			break;
		}
		default: {
			result = value;
		}
		}
		return result;
	}
	public String findCoordinate(String sanMove){
	int i = 0;

	char letter = sanMove.charAt(i);
	Convertor converter = new PGNXToCoordinate();
	converter.init();
	Integer column = converter.convert(letter + "");
	if (column == null) {
		log.error(sanMove);
	}
	i++;
	converter = new PGNYToCoordinate();
	converter.init();
	letter = sanMove.charAt(i);
	Integer line = converter.convert(letter + "");
	if (line == null) {
		log.error(sanMove);
	}
	return line + "-" + column;
}

}
