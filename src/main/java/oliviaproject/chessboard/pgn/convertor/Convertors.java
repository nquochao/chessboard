package oliviaproject.chessboard.pgn.convertor;

import java.util.HashSet;

public class Convertors extends HashSet<IConvertor> {
	public Convertors() {
		super();
		this.add(new Capture());
		this.add(new EchecRoi());
		this.add(new RecherchePrerequis());
		this.add(new PromotionPion());
		this.add(new Figures());

	}

	public IConvertor workPrerequis(String value, Boolean whiteToMove) {
		IConvertor result = null;
		for (int i = 0; i < this.size(); i++) {
			IConvertor c = this.iterator().next();
			c.load(value, whiteToMove);
			if (c.getTrigger()==Trigger.yes) {
				result=c;
				break;
			}
		}
		return result;
	}

}
