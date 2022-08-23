package oliviaproject.chessboard.pgn.convertor;

import java.util.HashSet;
import java.util.Iterator;

public class Convertors extends HashSet<IConvertor> {
	public Convertors() {
		super();
		this.add(new Prise());
		this.add(new PriseRecherchePrerequis());
		this.add(new Standard());
		this.add(new StandardPrerequis());
		this.add(new Figures());
		this.add(new PromotionPion());
		this.add(new EchecEtMat());
		this.add(new EchecRoi());
		this.add(new RocBig());
		this.add(new RocSmall());

	}

	public IConvertor workPrerequis(String value, Boolean whiteToMove) {
		Iterator<IConvertor> ic=iterator();
		IConvertor result = null;
		while(ic.hasNext()) {
			IConvertor c = ic.next();
			c.load(value, whiteToMove);
			if (c.getTrigger()==Trigger.yes) {
				result=c;
				break;
			}
		}
		return result;
	}

}
