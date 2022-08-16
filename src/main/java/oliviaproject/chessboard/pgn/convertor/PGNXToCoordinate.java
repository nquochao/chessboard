package oliviaproject.chessboard.pgn.convertor;

import java.util.HashMap;

public class PGNXToCoordinate extends Convertor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] columns=new String[] {
			"a", "b", "c", "d", "e", "f", "g", "h"
	};
	public void init() {
		for (int i=0;i<columns.length; i++) {
			String s=columns[i];
			this.put(s, i);
		}
	}
public Integer convert(String key) {
	return this.get(key);
}
}
