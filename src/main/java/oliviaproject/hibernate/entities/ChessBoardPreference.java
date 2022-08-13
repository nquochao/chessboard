package oliviaproject.hibernate.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ChessBoardPreference implements IEntity {
	@Id
    @GeneratedValue
	Integer id;

	@OneToOne	(mappedBy = "preference")
	private UserName userName;
/**
 * here userName is the owner and links to ChessBoardPreference via field username.preference.
 * mappedBy means ChessBoardPreference is not the owner but referred to in userName.preference
 * 
 */
	String colorTileWhite;
	String colorTileBlack;
	String colorPieceWhite;
	String colorPieceBlack;
	int chesswidth;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}
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
