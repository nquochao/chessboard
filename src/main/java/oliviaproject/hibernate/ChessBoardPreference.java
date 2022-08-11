package oliviaproject.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class ChessBoardPreference {
	String colorTileWhite;
	String colorTileBlack;
	String colorPieceWhite;
	String colorPieceBlack;
	int chesswidth;
	@Id
    @GeneratedValue
	private int id;
	@OneToOne
	@JoinColumn(name = "userName")
	private UserName userName;

	public UserName getUserName() {
		return userName;
	}

	public void setUserName(UserName userName) {
		this.userName = userName;
	}

	public String getColorTileWhite() {
		return colorTileWhite;
	}

	public void setColorTileWhite(String colorTileWhite) {
		this.colorTileWhite = colorTileWhite;
	}

	public String getColorTileBlack() {
		return colorTileBlack;
	}

	public void setColorTileBlack(String colorTileBlack) {
		this.colorTileBlack = colorTileBlack;
	}

	public String getColorPieceWhite() {
		return colorPieceWhite;
	}

	public void setColorPieceWhite(String colorPieceWhite) {
		this.colorPieceWhite = colorPieceWhite;
	}

	public String getColorPieceBlack() {
		return colorPieceBlack;
	}

	public void setColorPieceBlack(String colorPieceBlack) {
		this.colorPieceBlack = colorPieceBlack;
	}

	public int getChesswidth() {
		return chesswidth;
	}

	public void setChesswidth(int chesswidth) {
		this.chesswidth = chesswidth;
	}
}
