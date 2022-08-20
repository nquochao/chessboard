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

import oliviaproject.chessboard.pgn.convertor.Convertor;
import oliviaproject.chessboard.pgn.convertor.Convertors;
import oliviaproject.chessboard.pgn.convertor.IConvertor;
import oliviaproject.chessboard.pgn.convertor.PGNXToCoordinate;
import oliviaproject.chessboard.pgn.convertor.PGNYToCoordinate;
import oliviaproject.ui.dashboard.util.Piece;

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
						currentGameHeader.setAttribute(pole[0].substring(1, pole[0].length() - 1), // attribute name
								pole[1]);
					} else {
						parsingTagpairs = false;
					}
				} else {
					if (!line.startsWith("[")) {
						moveText.append(line);
						moveText.append(" ");
					} else {
						parseMoveText(moveText.toString());
						moveText = new StringBuilder();
						listOfGames.add(currentGame);
						if (line.startsWith("[")) {
							parsingTagpairs = true;
							currentGame = new GameStateMutable();
							currentGameHeader = currentGame.getGameHeader();
							String[] pole = line.split("\"");
							currentGameHeader.setAttribute(pole[0].substring(1, pole[0].length() - 1), // attribute
																										// name
									pole[1]);
						}
					}
				}
			}
			parseMoveText(moveText.toString());

			listOfGames.add(currentGame);
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

	/**
	 * Parse given movetext and set state-changing information of currentGame.
	 *
	 * @param moveText String containing concatenated lines of movetext
	 */
	private void parseMoveText(String moveText) {
		String[] sanMoves = removeBalastChars(moveText);

		boolean isWhiteToMove = true;
		for (String sanMove : sanMoves) {
			if (isSanMove(sanMove)) {
				log.info(sanMove);
				Move nextMove = san2Move(sanMove);
				currentGame.getMoves().add(nextMove);
				// changeSideToMove();
			}
		}
	}

	/**
	 * This method converts SAN (Short Algebraic Notation) String representation of
	 * the move into MoveInfo Object.
	 */
	private static Move san2Move(String sanMove) {
		if (sanMove == null) {
			throw new NullPointerException("sanMove can't be null!");
		}
		if (sanMove.length() < 2) {
			throw new IllegalArgumentException("no legal sanMove can have" + " less than 2 characters!:" + sanMove);
		}

		Boolean whiteToMove = true;
		Piece piece = Piece.determinePiece(sanMove, whiteToMove);
		String to = findPosition(sanMove);
		String from = findPosition(sanMove);

		return new Move(piece, from, to);
	}

	static Convertors convertors = new Convertors();

	/**
	 * @param sanMove
	 * @return
	 */
	private static IConvertor findPosition2(String sanMove) {
		IConvertor convertor = convertors.workPrerequis(sanMove, true);
		convertor.load(sanMove, true);
		return convertor;
	}

	private static String findPosition(String sanMove) {
		int i = 0;
		char letter = sanMove.charAt(i);
		if (Character.isUpperCase(letter)) {
			i++;
		}
		letter = sanMove.charAt(i);
		Convertor converter = new PGNXToCoordinate();
		converter.init();
		Integer column = converter.convert(letter + "");
		if (column == null) {
			log.error(sanMove);
		}
		i++;
		converter = new PGNYToCoordinate();
		converter.init();
		letter = sanMove.charAt(i);
		Integer line = converter.convert(letter + "");
		if (line == null) {
			log.error(sanMove);
		}
		return line + "-" + column;
	}
}
