package oliviaproject.chessboard.pgn.convertor;

public class PGNYToCoordinate extends Convertor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] columns=new String[] {
			"1", "2", "3", "4", "5", "6", "7", "8"
	};
	public void init() {
		for (int i=0;i<columns.length; i++) {
			String s=columns[i];
			this.put(s, 8-Integer.valueOf(s));
		}
	}
public Integer convert(String key) {
	return this.get(key);
}
}
