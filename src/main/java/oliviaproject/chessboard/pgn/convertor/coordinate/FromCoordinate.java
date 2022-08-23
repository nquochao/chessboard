package oliviaproject.chessboard.pgn.convertor.coordinate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.convertor.AbstractConvertor;
import oliviaproject.chessboard.pgn.convertor.Convertor;
import oliviaproject.chessboard.pgn.convertor.PGNXToCoordinate;
import oliviaproject.chessboard.pgn.convertor.PGNYToCoordinate;
import oliviaproject.ui.position.Position;

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
		switch (sanMove.length()) {
		case 1: {
			if (Character.isDigit(sanMove.charAt(0))) {
				int y= converterY.convert(sanMove);
				Position p=new Position();
				p.setY(y);
				p.setX(Position.ALL_VALUES);
				
				result = p.coordinate();
			} else {
				int x= converterX.convert(sanMove);
				Position p=new Position();
				
				p.setX(x);
				p.setY(Position.ALL_VALUES);
				result = p.coordinate();
	
			}
			break;
		}
		case 2: {
			result = findCoordinates(sanMove);

		}
		}
		return result;
	}

	protected String findCoordinates(String sanMove) {

		int i = 0;

		char letter = sanMove.charAt(i);

		Integer x = converterX.convert(letter + "");
		if (x == null) {
			log.error(sanMove);
		}
		i++;
		letter = sanMove.charAt(i);
		Integer y = converterY.convert(letter + "");
		if (y == null) {
			log.error(sanMove);
		}
		Position p = new Position();
		p.setX(x);
		p.setY(y);
		return p.coordinate(); // see {link Position.coordinate())
	}

}
