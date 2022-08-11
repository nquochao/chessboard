package oliviaproject.ui.dashboard;

import static oliviaproject.ui.dashboard.util.DashBoardUtil.NUMBER_COLUMNS;
import static oliviaproject.ui.dashboard.util.DashBoardUtil.NUMBER_ROWS;
import static oliviaproject.ui.dashboard.util.DashBoardUtil.TILE_X;
import static oliviaproject.ui.dashboard.util.DashBoardUtil.TILE_Y;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.ChessEvent;
import oliviaproject.event.Event;
import oliviaproject.event.EventListener;
import oliviaproject.ui.dashboard.util.IChessboardPanel;
import oliviaproject.ui.dashboard.util.NumberToLetter;
import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.dashboard.util.PlayMode;
import oliviaproject.ui.dashboard.util.SelectorRectangle;
import oliviaproject.ui.dashboard.util.Side;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.position.Positions;
import oliviaproject.ui.possiblemove.PositionCavalier;
import oliviaproject.ui.possiblemove.PositionFou;
import oliviaproject.ui.possiblemove.PositionReine;
import oliviaproject.ui.possiblemove.PositionTour;
import oliviaproject.ui.possiblemove.PositionUtil;
import oliviaproject.ui.promotion.ChessPiecePromotion;

public class OliviaPanel extends JPanel implements IChessboardPanel, EventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int xlength = TILE_X;
	int ylength = TILE_Y;
	int xinit = 0;
	int yinit = 0;
	Color colorBlackTile = COLOR_TILE_BLACK, colorWhiteTile = COLOR_TILE_WHITE;
	Positions ps = new Positions();
	Position lastPosition = new Position();
	int numbercols = NUMBER_COLUMNS;
	int numberrows = NUMBER_ROWS;
	Map<String, Rectangle> quads = new HashMap<>();
	private SelectorRectangle selected;
	private SelectorRectangle clickedSelectedOrigin, clickedSelectedTarget;

	Boolean addCoordinates = true;
	private PlayMode playMode;

	public Boolean getAddCoordinates() {
		return addCoordinates;
	}

	public void setAddCoordinates(Boolean addCoordinates) {
		this.addCoordinates = addCoordinates;
	}

	public void initialize() throws IOException {
		ps.clear();
		for (int j = 0; j < numberrows; j++) {
			try {
				fillrow(j, Side.White);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		MouseAdapter ma = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				clickedSelectedOrigin = null;
				for (String key : quads.keySet()) {
					Rectangle cell = quads.get(key);
					if (cell.contains(e.getPoint())) {
						clickedSelectedOrigin = new SelectorRectangle();
						clickedSelectedOrigin.setRectangle(cell);
						clickedSelectedOrigin.setCoordinate(key);
						if (playMode == PlayMode.game) {
							if (!checkPositionSelectedPiece(key)) {
								clickedSelectedOrigin = null;

								// we do not allow for selection if not the side to play
							}
						}

						break;
					}
				}
			}

			private void updateNextPlayer(String key) {
				Position selectedPosition = ps.get(key);

				if (selectedPosition != null && selectedPosition.getPiece() != Piece.None) {
					int nextvalue = (playMode.getSideToPlay().ordinal() + 1) % Side.values().length;
					Side s = Side.values()[nextvalue];
					if (s == Side.None)
						nextvalue = (nextvalue + 1) % Side.values().length;
					s = Side.values()[nextvalue];
					playMode.setSideToPlay(s);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				clickedSelectedTarget = null;
				for (String key : quads.keySet()) {
					Rectangle cell = quads.get(key);
					if (cell.contains(e.getPoint())) {
						clickedSelectedTarget = new SelectorRectangle();
						clickedSelectedTarget.setRectangle(cell);
						clickedSelectedTarget.setCoordinate(key);
						if (clickedSelectedOrigin != null) {

							Position p = ps.get(clickedSelectedOrigin.getCoordinate());
							Set<String> possiblepositions = p.findPossibleMove(ps, lastPosition);

							if (clickedSelectedTarget != null) {
								Position pTarget = ps.get(clickedSelectedTarget.getCoordinate());
//								if(pTarget.coordinate().equals(p.coordinate())){
//									return;// this is not necessary but in case possiblepositions not well calculated
//								}
								if (possiblepositions != null && possiblepositions.contains(pTarget.coordinate())) {
									;
									PositionUtil.doRock(pTarget, p, ps);
									PositionUtil.doEnPassant(pTarget, p, ps, lastPosition);

									ps.get(pTarget.coordinate()).setPiece(p.getPiece());
									PositionUtil.updatePiece(pTarget, ps);
									lastPosition.updatePosition(pTarget);

									// we check if it is a rock move

									ps.get(p.coordinate()).setPiece(Piece.None);
									PositionUtil.updatePiece(p, ps);
									PositionUtil.doPromotion(pTarget, ps);
									if (playMode == PlayMode.game) {
										updateNextPlayer(key);
									}
								}

							}
						}
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				selected = null;
				for (String key : quads.keySet()) {
					Rectangle cell = quads.get(key);
					if (cell.contains(e.getPoint())) {
						selected = new SelectorRectangle();
						selected.setRectangle(cell);
						selected.setCoordinate(key);

						break;
					}
				}
				repaint();
			}
		};
		addMouseMotionListener(ma);
		addMouseListener(ma);
	}

	private void fillrowTitle(int rownumber, Graphics2D g2d) {
		int y = yinit + rownumber * ylength;
		NumberToLetter numberToLetter = new NumberToLetter();
		if (this.addCoordinates) {
			int x = xinit + 0 * xlength;

			switch (playMode.getSideToPlay()) {
			case Black: {
				g2d.setColor(Color.BLACK);
				break;
			}
			case White: {
				g2d.setColor(Color.WHITE);
				break;
			}
			}

			g2d.fillRect(x, y, xlength, ylength); // Draws square

		}
		for (int i = 0; i < numbercols; i++) {
			int x = xinit + i * xlength;
			if (this.addCoordinates)
				x = x + xlength;
			g2d.setColor(Color.WHITE);

			g2d.drawRect(x, y, xlength, ylength); // Draws square
			// Draw image inside square
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

			Position p = ps.get(i + Position.separator + rownumber);
			g2d.setColor(Color.BLACK);
			g2d.drawString(numberToLetter.get(i), x + (xlength) / 2, y + (ylength) / 2);

		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // ALWAYS call this method first!
		Graphics2D g2d = (Graphics2D) g;
//	    int w = getSize().width;
//	    int h = getSize().height;
//	    int zoomFactor=2;
//	    int prevZoomFactor=1;
//	    boolean zoomer=true;
//	    if (zoomer) {
//	        AffineTransform at = new AffineTransform();
//	        at.scale(zoomFactor, 1);
//	        prevZoomFactor = zoomFactor;
//	        g2d.transform(at);
//	        zoomer = false;
//	    }

		for (int j = 0; j < numberrows; j++) {
			try {
				fillrow(j, g2d);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (selected != null) {
				Color color = COLOR_TILE_SELECTED;
				g2d.setColor(color);

				Position p = ps.get(selected.getCoordinate());
				Set<String> possiblepositions = p.findPossibleMove(ps, lastPosition);
				boolean checkWhoIsNextPlayer = checkPositionSelectedPiece(p);
				if (possiblepositions != null) {
					for (String coordinate : possiblepositions) {
						Rectangle cell = quads.get(coordinate);
						if (playMode == PlayMode.game) {

							if (checkWhoIsNextPlayer) {

								g2d.fill(cell);
							}
						}
						if (playMode == PlayMode.test)
							g2d.fill(cell);
					}
				}
				color = COLOR_TILE_CLICK_SELECTED;
				g2d.setColor(color);
				g2d.fill(selected.getRectangle());
			}

		}
		if (addCoordinates)
			fillrowTitle(numberrows, g2d);

		g2d.dispose();
	}

	private void fillrow(int rownumber, Side side) throws IOException {

		for (int i = 0; i < numbercols; i++) {
			Position p = new Position(i, rownumber);
			ps.put(p.coordinate(), p);
			if (side != Side.None)
				ps.initialize(side);

		}
		int y = yinit + rownumber * ylength;
		for (int i = 0; i < numbercols; i++) {
			int x = xinit + i * xlength;
			if (addCoordinates) {

				x += xlength;

			}
			quads.put(i + "," + rownumber, new Rectangle(x, y, xlength, ylength));
		}
	}

	private void fillrow(int rownumber, Graphics2D g2d) throws IOException {
		int y = yinit + rownumber * ylength;
		for (int i = 0; i < numbercols; i++) {
			int x = xinit + i * xlength;
			if (addCoordinates) {
				addOrdonnee(i, g2d);
				x += xlength;

			}

			boolean b1 = i % 2 == 0;
			boolean b2 = rownumber % 2 == 0;
			g2d.setColor(colorWhiteTile);
			if (b2) {

				if (b1)
					g2d.setColor(colorBlackTile);

			} else {
				if (!b1)
					g2d.setColor(colorBlackTile); // Draws square

			}
			g2d.fillRect(x, y, xlength, ylength); // Draws square
			// Draw image inside square
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

			Position p = ps.get(i + Position.separator + rownumber);
			g2d.drawImage(p.getPiece().getImg(), x, y, xlength, ylength, null);

		}

	}

	private void addOrdonnee(int i, Graphics2D g2d) {
		int x = xinit;
		int y = yinit + ylength * i;
		g2d.setColor(Color.WHITE);

		g2d.drawRect(x, y, xlength, ylength); // Draws square
		// Draw image inside square
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g2d.setColor(Color.BLACK);
		g2d.drawString(String.valueOf(8 - i), x + (xlength) / 2, y + (ylength) / 2);
	}

	@Override
	public Dimension getPreferredSize() {
		int x = xinit + (numbercols) * xlength;
		int y = yinit + numbercols * ylength;
		if (addCoordinates) {
			x = x + xlength;
			y = y + ylength;
		}
		return new Dimension(x, y);
	}

	public void setPlayMode(PlayMode playmode) {
		this.playMode = playmode;

	}

	private boolean checkPositionSelectedPiece(String key) {
		Position selectedPosition = ps.get(key);
		boolean result = false;
		if (selectedPosition != null) {
			result = selectedPosition.getPiece().getSide() == playMode.getSideToPlay();
		}
		return result;
	}

	private boolean checkPositionSelectedPiece(Position position) {

		boolean result = false;

		result = position.getPiece().getSide() == playMode.getSideToPlay();

		return result;
	}

	@Override
	public void onMyEvent(Event event) {
		switch(event.getClass().getName()) {
		case "oliviaproject.event.ChessEvent":{
			manageChessEvent((ChessEvent)event);
			break;
		}
		case "oliviaproject.event.ChessEchelleEvent":{
			manageChessEchelleEvent((ChessEchelleEvent)event);
			break;
		}		
		case "oliviaproject.event.ChessColorDashBoardEvent":{
			manageChessColorDashBoardEvent((ChessColorDashBoardEvent)event);
			break;
		}		
	case "oliviaproject.event.ChessColorPieceEvent":{
		manageChessColorPieceEvent((ChessColorPieceEvent)event);
		break;
	}		
	
		}
	}

	private void manageChessColorPieceEvent(ChessColorPieceEvent event) {
		Color colorBlack=event.getColor2();
		Color colorWhite=event.getColor1();
		OliviaPanel p=this;
		for(String ref :ps.keySet()) {
			Position mp=ps.get(ref);
			Piece piece=mp.getPiece();
			if(piece.getSide()==Side.White)piece.setColor(colorWhite);
			if(piece.getSide()==Side.Black)piece.setColor(colorBlack);
			piece.reloadImg();
		}
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				p.repaint();
			}});		
	

	}

	private void manageChessColorDashBoardEvent(ChessColorDashBoardEvent event) {
		this.colorBlackTile = event.getColor2();
		this.colorWhiteTile = event.getColor1();
		OliviaPanel p = this;

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				p.repaint();
			}
		});
	}

	private void manageChessEchelleEvent(ChessEchelleEvent event) {
		int zoom = event.getZoom();
		xlength = zoom;
		ylength = zoom;
		OliviaPanel p = this;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				p.repaint();
				for (int j = 0; j < numberrows; j++) {

					int y = yinit + j * ylength;
					for (int i = 0; i < numbercols; i++) {
						int x = xinit + i * xlength;
						if (addCoordinates) {

							x += xlength;

						}
						quads.put(i + "," + j, new Rectangle(x, y, xlength, ylength));
					}
				}
			}
		});

	}

	private void manageChessEvent(ChessEvent chessEvent) {

		ChessPiecePromotion chosen = chessEvent.getPromotion();
		Position promotion = chessEvent.getPosition();
		promotion = ps.get(promotion.coordinate());
		switch (chosen) {
		case reine: {
			if (promotion.getPiece().getSide() == Side.White)
				promotion.setPiece(Piece.DameW);
			if (promotion.getPiece().getSide() == Side.Black)
				promotion.setPiece(Piece.DameB);
			promotion.getPiece().setPossibleMove(new PositionReine());
			promotion.getPiece().getPossibleMove().init(promotion);
			promotion.getPiece().getPossibleMove().setPieceHasMoved(true);

			break;
		}
		case tour: {
			if (promotion.getPiece().getSide() == Side.White)
				promotion.setPiece(Piece.TourWLittleRock);
			if (promotion.getPiece().getSide() == Side.Black)
				promotion.setPiece(Piece.TourBLittleRock);
			promotion.getPiece().setPossibleMove(new PositionTour());
			promotion.getPiece().getPossibleMove().init(promotion);
			promotion.getPiece().getPossibleMove().setPieceHasMoved(true);
			break;
		}
		case fou: {
			if (promotion.getPiece().getSide() == Side.White)
				promotion.setPiece(Piece.FouW);
			if (promotion.getPiece().getSide() == Side.Black)
				promotion.setPiece(Piece.FouB);
			promotion.getPiece().setPossibleMove(new PositionFou());
			promotion.getPiece().getPossibleMove().init(promotion);
			promotion.getPiece().getPossibleMove().setPieceHasMoved(true);

			break;
		}

		case cavalier: {
			if (promotion.getPiece().getSide() == Side.White)
				promotion.setPiece(Piece.CavalierW);
			if (promotion.getPiece().getSide() == Side.Black)
				promotion.setPiece(Piece.CavalierB);
			promotion.getPiece().setPossibleMove(new PositionCavalier());
			promotion.getPiece().getPossibleMove().init(promotion);
			promotion.getPiece().getPossibleMove().setPieceHasMoved(true);

			break;
		}
		}
	}
}
