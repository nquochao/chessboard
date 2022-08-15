package oliviaproject.chessboard.pgn;

public class GameHeader {
String event, site, date, round, white, black, result;

public String getEvent() {
	return event;
}

public void setEvent(String event) {
	this.event = event;
}

public String getSite() {
	return site;
}

public void setSite(String site) {
	this.site = site;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getRound() {
	return round;
}

public void setRound(String round) {
	this.round = round;
}

public String getWhite() {
	return white;
}

public void setWhite(String white) {
	this.white = white;
}

public String getBlack() {
	return black;
}

public void setBlack(String black) {
	this.black = black;
}

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}
}
