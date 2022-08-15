package oliviaproject.chessboard.pgn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PGNReader {
	GameStateMutable currentGame = new GameStateMutable();
	Boolean parsingTagpairs = true;
	static final Logger log = LoggerFactory.getLogger(PGNReader.class);

	/**
	 * Given pgn file this method returns list of games contained in the file.
	 *
	 * @param f the pgn file to be parsed.
	 * @return The list of games stored in the file.
	 */
	public List<GameStateMutable> parseFile(InputStream inputStream) {
		ArrayList<GameStateMutable> listOfGames = new ArrayList<GameStateMutable>();
		BufferedReader inputReader = null;
		parsingTagpairs = true;

		try {
			InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			inputReader = new BufferedReader(streamReader);
			String line = null;

			GameHeader currentGameHeader = currentGame.getGameHeader();
			
			StringBuilder moveText = new StringBuilder();

			while ((line = inputReader.readLine()) != null) {
				if (parsingTagpairs) { 
					if (line.startsWith("[")) {
						String[] pole = line.split("\"");
						setAttribute(currentGameHeader, pole[0].substring(1, pole[0].length() - 1), // attribute name
								pole[1]); // attribute value
					} else {
						parsingTagpairs = false; // zahodime current lradek (dle PGN Spec. zarucene prazdny)
					}
				} else { // parsujeme muvtext
					if (!line.startsWith("[")) {
						// pridej ten radek k movetextu
						moveText.append(line);
						moveText.append(" "); // mezera pro oddeleni radku
					} else { 
						//arseMoveText(moveText.toString());
						moveText = new StringBuilder();
						listOfGames.add(currentGame);
						if (line.startsWith("[")) {
							parsingTagpairs = true;
							currentGame = new GameStateMutable();
							currentGameHeader = currentGame.getGameHeader();
							String[] pole = line.split("\"");
							setAttribute(currentGameHeader, pole[0].substring(1, pole[0].length() - 1), // attribute
																										// name
									pole[1]); // attribute value
						}
					}
				}
			}
			listOfGames.add(currentGame);
			System.out.println("Uspesne rozparsovano " + listOfGames.size() + " her!");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputReader != null) {
					inputReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return listOfGames;
	}

	/**
	 * Sets attribute with givenName in given GameHeader to given attibute value.
	 *
	 * @param header    header of game in which we want to set attrName to attrValue
	 * @param attrName  name of attribute to set
	 * @param attrValue value of attribute which will be set
	 */
	private void setAttribute(GameHeader header, String attrName, String attrValue) {
		attrName = attrName.toLowerCase();
		switch (attrName) {
		case "event":
			header.setEvent(attrValue);
			break;
		case "site":
			header.setSite(attrValue);
			break;
		case "date":
			header.setDate(attrValue);
			break;
		case "round":
			header.setRound(attrValue);
			break;
		case "white":
			header.setWhite(attrValue);
			break;
		case "black":
			header.setBlack(attrValue);
			break;
		case "result":
			header.setResult(attrValue);
			break;
		}
	}

	/**
	 * Removes redundant characters and symbols from given movetext. For example for
	 * input movetext "1.f3 e5 2.g4 Qh4# 0-1" the output is array [f3, e5, g4, Qh4,
	 * 0-1].
	 */
	private static String[] removeBalastChars(String moveText) {
		String[] pole = moveText.split(" ");

		for (int i = 0; i < pole.length; i++) {
			// odstran cislovani tahu s teckami
			if (pole[i].contains(".")) {
				pole[i] = pole[i].substring(pole[i].indexOf('.') + 1);
			}
			// odstran znaky '+'
			if (pole[i].endsWith("+") || pole[i].endsWith("#")) {
				pole[i] = pole[i].substring(0, pole[i].length() - 1);
			}
			// odstran 'x' symbolizujici capture
			if (pole[i].contains("x")) {
				pole[i] = pole[i].substring(0, pole[i].indexOf('x'))
						+ pole[i].substring(pole[i].indexOf('x') + 1, pole[i].length());
			}
		}
		return pole;
	}

	/**
	 * This method checks, whether given moveTextToken can be token representing
	 * move or is just some other information token contained in movetext ( result
	 * information, empty string etc).
	 */
	private static boolean isSanMove(String moveTextToken) {
		// neni prazdny retezec ani to nenivysledek
		if (moveTextToken.length() != 0 && !moveTextToken.startsWith("0") && !moveTextToken.startsWith("1")
				&& !moveTextToken.startsWith("*")) {
			return true;
		} else {
			return false;
		}
	}

}
