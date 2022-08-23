package oliviaproject.chessboard.pgn.convertor.coordinate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.convertor.AbstractConvertor;
import oliviaproject.chessboard.pgn.convertor.Convertor;
import oliviaproject.chessboard.pgn.convertor.PGNXToCoordinate;
import oliviaproject.chessboard.pgn.convertor.PGNYToCoordinate;

public class ToCoordinate implements ICoordinate {
	protected static final Logger log = LoggerFactory.getLogger(AbstractConvertor.class);

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
