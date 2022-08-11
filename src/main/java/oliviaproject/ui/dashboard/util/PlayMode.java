package oliviaproject.ui.dashboard.util;

public enum PlayMode {
game(Side.White), test(Side.None);

PlayMode(Side side) {
	this.sideToPlay=side;
}
Side sideToPlay;
public Side getSideToPlay() {
	return sideToPlay;
}
public void setSideToPlay(Side sideToPlay) {
	this.sideToPlay = sideToPlay;
}
}
