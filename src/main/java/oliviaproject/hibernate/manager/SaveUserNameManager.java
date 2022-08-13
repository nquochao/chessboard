package oliviaproject.hibernate.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.Default;
import oliviaproject.event.Event;
import oliviaproject.eventbus.EventListener;
import oliviaproject.hibernate.dao.UserNameSQL;
import oliviaproject.hibernate.entities.ChessBoardPreference;
@Component
public class SaveUserNameManager implements EventListener {
	@Autowired
	UserNameSQL userSQL;
	
public void init() {
}
	@Override
	public void onMyEvent(Event event) {
		if (event instanceof ChessColorDashBoardEvent) {
			ChessColorDashBoardEvent myevent = (ChessColorDashBoardEvent) event;
			if(Default.getUserName().getPreference()==null)Default.getUserName().setPreference(new ChessBoardPreference());
			if(myevent.getColorWhiteTile()!=null)Default.getUserName().getPreference().setColorTileWhite(String.valueOf(myevent.getColorWhiteTile().getRGB()));
			if(myevent.getColorBlackTile()!=null)Default.getUserName().getPreference().setColorTileBlack(String.valueOf(myevent.getColorBlackTile().getRGB()));
			
		}else if (event instanceof ChessColorPieceEvent) { 
			ChessColorPieceEvent myevent = (ChessColorPieceEvent) event;
			if(Default.getUserName().getPreference()==null)Default.getUserName().setPreference(new ChessBoardPreference());
			if(myevent.getColorWhite()!=null)Default.getUserName().getPreference().setColorPieceWhite(String.valueOf(myevent.getColorWhite().getRGB()));
			if(myevent.getColorWhite()!=null)Default.getUserName().getPreference().setColorPieceBlack(String.valueOf(myevent.getColorBlack().getRGB()));

		}else if (event instanceof ChessEchelleEvent) {
			ChessEchelleEvent myevent = (ChessEchelleEvent) event;
			if(Default.getUserName().getPreference()==null)Default.getUserName().setPreference(new ChessBoardPreference());
			Default.getUserName().getPreference().setChesswidth(myevent.getZoom());
		}else if (event instanceof ChessColorSelectEvent) {
			ChessColorSelectEvent myevent = (ChessColorSelectEvent) event;
			if(myevent.getColorSelect()!=null)Default.getUserName().getPreference().setColorSelected(String.valueOf(myevent.getColorSelect().getRGB()));
			if(myevent.getColorPossible()!=null)Default.getUserName().getPreference().setColorPossible(String.valueOf(myevent.getColorPossible().getRGB()));

		}
		userSQL.save(Default.getUserName());
	
	}

}
