package oliviaproject.chessboard.pgn.convertor.coordinate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.convertor.AbstractConvertor;
import oliviaproject.chessboard.pgn.convertor.Convertor;
import oliviaproject.chessboard.pgn.convertor.PGNXToCoordinate;
import oliviaproject.chessboard.pgn.convertor.PGNYToCoordinate;

public class FromCoordinate implements ICoordinate {
Convertor converterX, converterY;
	public FromCoordinate() {
		 converterX = new PGNXToCoordinate();
		 converterY = new PGNYToCoordinate();
			converterX.init();
			converterY.init();
	}
	protected static final Logger log = LoggerFactory.getLogger(AbstractConvertor.class);
	public String findCoordinate(String sanMove) {
		String result = new String();
		switch(sanMove.length()) {
		case 1:{
			if(Character.isDigit(sanMove.charAt(0))) {
				result= ""+converterY.convert(sanMove);
			}else {
				result= ""+converterX.convert(sanMove);
			}
			break;
		}
		case 2:{
			result = findCoordinates(sanMove);
			
		}
		}
		return result;
	}
	protected String findCoordinates(String sanMove){
	
	int i = 0;

	char letter = sanMove.charAt(i);
	
	Integer column = converterX.convert(letter + "");
	if (column == null) {
		log.error(sanMove);
	}
	i++;
	letter = sanMove.charAt(i);
	Integer line = converterY.convert(letter + "");
	if (line == null) {
		log.error(sanMove);
	}
	return line + "-" + column;
}

}
