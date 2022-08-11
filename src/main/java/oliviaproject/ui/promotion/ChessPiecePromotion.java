package oliviaproject.ui.promotion;

public enum ChessPiecePromotion {
	
		 reine("\u2655"), tour("\u2656"), fou("\u2657"), cavalier("\u2658"), pion("\u2659");

	ChessPiecePromotion(String value) {
			unicode = value;
		}

		String unicode;

		public String getUnicode() {
			return unicode;
		}
	

}
