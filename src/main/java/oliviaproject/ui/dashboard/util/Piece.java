package oliviaproject.ui.dashboard.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import oliviaproject.ui.position.Position;
import oliviaproject.ui.possiblemove.PositionCavalier;
import oliviaproject.ui.possiblemove.PositionFou;
import oliviaproject.ui.possiblemove.PositionNone;
import oliviaproject.ui.possiblemove.PositionPionB;
import oliviaproject.ui.possiblemove.PositionPionW;
import oliviaproject.ui.possiblemove.PositionReine;
import oliviaproject.ui.possiblemove.PositionRoi;
import oliviaproject.ui.possiblemove.PositionTour;

public enum Piece implements IPiece {

	RoiW(Side.White, new PositionRoi(), "\u2654", COLOR_WHITE, COLOR_WHITE_OUTLINE),
	DameW(Side.White, new PositionReine(), "\u2655", COLOR_WHITE, COLOR_WHITE_OUTLINE),
	//TourWLongRock(Side.White, new PositionTour(), "\u2656", COLOR_WHITE, COLOR_WHITE_OUTLINE),
	TourW(Side.White,new PositionTour(),"\u2656", COLOR_WHITE,COLOR_WHITE_OUTLINE),
	FouW(Side.White, new PositionFou(), "\u2657", COLOR_WHITE, COLOR_WHITE_OUTLINE),
	CavalierW(Side.White, new PositionCavalier(), "\u2658", COLOR_WHITE, COLOR_WHITE_OUTLINE),
	PionW(Side.White, new PositionPionW(), "\u2659", COLOR_WHITE, COLOR_WHITE_OUTLINE),
	RoiB(Side.Black, new PositionRoi(), "\u2654", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	DameB(Side.Black, new PositionReine(), "\u2655", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	//TourBLongRock(Side.Black, new PositionTour(), "\u2656", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	TourB(Side.Black, new PositionTour(), "\u2656", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	FouB(Side.Black, new PositionFou(), "\u2657", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	CavalierB(Side.Black, new PositionCavalier(), "\u2658", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	PionB(Side.Black, new PositionPionB(), "\u2659", COLOR_BLACK, COLOR_BLACK_OUTLINE),
	None(Side.None, new PositionNone(), "", COLOR_BLACK, COLOR_BLACK_OUTLINE);

	private BufferedImage img;

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	private String unicode;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	private Color colorOutline;

	public BufferedImage getImg() {
		if (this == None)
			return null;

		return img;
	}

	@Deprecated
	public BufferedImage loadImage(String img) {
		BufferedImage bi = null;
		try {
			if (this == None)
				return bi;
			bi = ImageIO.read(new File(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	Piece(Side side, Position position, String unicode, Color color, Color colorOutline) {
		this.side = side;
		this.unicode = unicode;
		this.color = color;
		this.colorOutline = colorOutline;
		positionPossibleMove = position;
		boolean blackSquare = false;

		img = ChessBoardPieceImage.addColoredUnicodeCharToBufferedImage(unicode, color, colorOutline, blackSquare);
	}

	public void reloadImg() {
		boolean blackSquare = false;

		img = ChessBoardPieceImage.addColoredUnicodeCharToBufferedImage(unicode, color, colorOutline, blackSquare);
	}

	Side side;
	Position positionPossibleMove;

	public void setPossibleMove(Position positionPossibleMove) {
		this.positionPossibleMove = positionPossibleMove;
	}

	public Position getPossibleMove() {
		return positionPossibleMove;
	}

	public Side getSide() {
		return side;
	}

	public static Piece determinePiece(String sanMove, Boolean isWhiteToMove) {
		char firstLetter = sanMove.charAt(0);
		Piece piece = null;

		// determine piece from the first letter
		switch (firstLetter) {
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
			piece = (isWhiteToMove ? PionW : PionB);
			break;
		case 'B':
			piece = (isWhiteToMove ? FouW : FouB);
			break;
		case 'K':
		case 'O': // rosada zacina Ockem (NE NULOU!)
			piece = (isWhiteToMove ? RoiW : RoiB);
			break;
		case 'N':
			piece = (isWhiteToMove ? CavalierW : CavalierB);
			break;
		case 'Q':
			piece = (isWhiteToMove ? DameW : DameB);
			break;
		case 'R':
			piece = (isWhiteToMove ? TourW : TourB);
			break;
		default:
			throw new IllegalStateException("I have sanMove, which does NOT" + " start with [abcdefghBKNOQR]!");
		}
		return piece;
	}

}
