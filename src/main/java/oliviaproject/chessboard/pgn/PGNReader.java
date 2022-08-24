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

import oliviaproject.chessboard.pgn.convertor.Convertors;
import oliviaproject.chessboard.pgn.convertor.IConvertor;
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
			Boolean whiteToMove=true;

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
						whiteToMove=true;
						parseMoveText(moveText.toString());
						whiteToMove=!whiteToMove;
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
			whiteToMove=!whiteToMove;
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
		Boolean whiteToMove=true;
		for (String sanMove : sanMoves) {
			if (isSanMove(sanMove)) {
				log.info(sanMove);
				Move nextMove = san2Move(sanMove, whiteToMove);
				nextMove.setWhiteToPlay(whiteToMove);
				currentGame.getMoves().add(nextMove);
				// changeSideToMove();
				whiteToMove=!whiteToMove;
			}
		}
	}

	/**
	 * This method converts SAN (Short Algebraic Notation) String representation of
	 * the move into MoveInfo Object.
	 */
	private static Move san2Move(String sanMove, Boolean whiteToMove) {
		if (sanMove == null) {
			throw new NullPointerException("sanMove can't be null!");
		}
		if (sanMove.length() < 2) {
			throw new IllegalArgumentException("no legal sanMove can have" + " less than 2 characters!:" + sanMove);
		}

		Piece piece = Piece.determinePiece(sanMove, whiteToMove);
		log.info(sanMove);
		String to = findPositionTo(sanMove, convertors, whiteToMove);
		String from = findPositionFrom(sanMove, convertors, whiteToMove);
		//IConvertor convertor = findConvertor(sanMove, convertors);

		return new Move(piece, from, to, null);
	}

	static Convertors convertors = new Convertors();

	/**
	 * @param sanMove
	 * @return
	 */
	static IConvertor findConvertor(String sanMove, Convertors convertors, Boolean whiteToMove) {

		IConvertor convertor = convertors.workPrerequis(sanMove, whiteToMove);
		if (convertor == null) {
			log.error("There is a missing convertor: " + sanMove);
		} else {
			convertor.load(sanMove, whiteToMove);
		}
		return convertor;
	}

	public static String findPositionFrom(String sanMove, Convertors convertors, Boolean whiteToMove) {
		IConvertor convertor = findConvertor(sanMove, convertors,whiteToMove);
		String result = new String();
		result = convertor.getBeforeValue();

		return result;
	}

	public static String findPositionTo(String sanMove, Convertors convertors, Boolean whiteToMove) {
		IConvertor convertor = findConvertor(sanMove, convertors, whiteToMove);

		String result = convertor.getNextValue();

		return result;
	}

}
