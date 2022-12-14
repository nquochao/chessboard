package oliviaproject.chessboard.pgn.convertor;

public interface IConvertor {

	void load(String value, Boolean whiteToMove);

	Trigger getTrigger();
	public String getNextValue() ;
	public String getBeforeValue();
	public String getValue() ;
	public Boolean getWhiteToMove();
	public ConvertorType getConvertorType();

}