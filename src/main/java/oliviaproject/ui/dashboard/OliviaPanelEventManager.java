package oliviaproject.ui.dashboard;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Set;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.Move;
import oliviaproject.event.ChessColorDashBoardEvent;
import oliviaproject.event.ChessColorPieceEvent;
import oliviaproject.event.ChessColorSelectEvent;
import oliviaproject.event.ChessEchelleEvent;
import oliviaproject.event.ChessMoveEvent;
import oliviaproject.event.ChessPromotionEvent;
import oliviaproject.ui.dashboard.util.Piece;
import oliviaproject.ui.dashboard.util.Side;
import oliviaproject.ui.position.Position;
import oliviaproject.ui.possiblemove.PositionCavalier;
import oliviaproject.ui.possiblemove.PositionFou;
import oliviaproject.ui.possiblemove.PositionReine;
import oliviaproject.ui.possiblemove.PositionTour;
import oliviaproject.ui.promotion.ChessPiecePromotion;

public class OliviaPanelEventManager implements IEventManager {
	OliviaPanel panel;
	static final Logger log = LoggerFactory.getLogger(MoveAction.class);


	public OliviaPanelEventManager(OliviaPanel oliviaPanel) {
		this.panel = oliviaPanel;
	}

	/**
	 * @param event
	 */
	public void visit(ChessMoveEvent event) {
		MoveAction moveAction = new MoveAction();
		Move move = event.getMove();
		// we do not know pOrigin : Position pOrigin =
		// ps.get(clickedSelectedOrigin.getCoordinate());
		Position pOrigin = moveAction.calculatePossibleOriginPositions(move, panel.ps);
		
		String coordinate = move.getTo();
		Set<String> possiblepositions = pOrigin.findPossibleMove(panel.ps, panel.lastPosition);
		moveAction.move(coordinate, possiblepositions, panel.ps, pOrigin, panel.lastPosition);
		log.info(move.toString() +" has been treated");
	}

	public void visit(ChessColorSelectEvent event) {
		Color possibleColor = event.getColorPossible();
		Color selectColor = event.getColorSelect();
		if (selectColor != null) {
			panel.colorSelected = selectColor;
		}
		if (possibleColor != null) {
			panel.colorTileClickSelected = possibleColor;
		}

	}

	public void visit(ChessColorPieceEvent event) {
		Color colorWhite = event.getColorWhite();
		Color colorBlack = event.getColorBlack();
		OliviaPanel p = panel;
		for (String ref : panel.ps.keySet()) {
			Position mp = panel.ps.get(ref);
			Piece piece = mp.getPiece();
			boolean modifyWhite = piece.getSide() == Side.White && colorWhite != null;
			boolean modifyBlack = piece.getSide() == Side.Black && colorBlack != null;
			if (modifyWhite) {
				this.panel.colorPieceWhite = colorWhite;
				piece.setColor(colorWhite);
			}
			if (modifyBlack) {
				this.panel.colorPieceBlack = colorBlack;
				piece.setColor(colorBlack);
			}
			if (modifyWhite | modifyBlack)
				piece.reloadImg();
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				p.repaint();
			}
		});

	}

	public void visit(ChessColorDashBoardEvent event) {
		boolean modifyWhite = event.getColorWhite() != null;
		boolean modifyBlack = event.getColorBlack() != null;
		if (modifyWhite)
			this.panel.colorWhiteTile = event.getColorWhite();
		if (modifyBlack)
			this.panel.colorBlackTile = event.getColorBlack();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				panel.repaint();
			}
		});
	}

	public void visit(ChessEchelleEvent event) {
		int zoom = event.getZoom();
		panel.xlength = zoom;
		panel.ylength = zoom;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				panel.repaint();
				for (int j = 0; j < panel.numberrows; j++) {

					int y = panel.yinit + j * panel.ylength;
					for (int i = 0; i < panel.numbercols; i++) {
						int x = panel.xinit + i * panel.xlength;
						if (panel.addCoordinates) {

							x += panel.xlength;

						}
						panel.quads.put(i + "," + j, new Rectangle(x, y, panel.xlength, panel.ylength));
					}
				}
			}
		});

	}

	public void visit(ChessPromotionEvent chessEvent) {

		ChessPiecePromotion chosen = chessEvent.getPromotion();
		Position promotion = chessEvent.getPosition();
		promotion = panel.ps.get(promotion.coordinate());
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
				promotion.setPiece(Piece.TourW);
			if (promotion.getPiece().getSide() == Side.Black)
				promotion.setPiece(Piece.TourB);
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
