package oliviaproject.chessboard.pgn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.convertor.Convertors;
import oliviaproject.chessboard.pgn.convertor.IConvertor;
import oliviaproject.util.file.FileUtils;

public class PGNReaderTest {
	static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	String nameFile = "/oliviaproject/pgn/test-e.pgn";

	@Test
	public void testReadFile() {
		
		InputStream is = FileUtils.getFileFromResourceAsStream(nameFile);

		assertNotNull(is);
		PGNReader reader = new PGNReader();

		FileUtils.printInputStream(is);
	}
	@Test
	public void testReadFileFenComments() {
		String nameFile="/oliviaproject/pgn/test-fencomments.pgn";
		InputStream is = FileUtils.getFileFromResourceAsStream(nameFile);

		assertNotNull(is);
		PGNReader reader = new PGNReader();

		FileUtils.printInputStream(is);
	}
//	@Test
//	public void testParseFenComments() {
//		String nameFile="/oliviaproject/pgn/test-fencomments.pgn";
//		InputStream is = FileUtils.getFileFromResourceAsStream(nameFile);
//
//		assertNotNull(is);
//		PGNReader reader = new PGNReader();
//		List <GameStateMutable> games=reader.parseFile(is);
//		assertNotNull(games);
//		assertEquals(games.size(), 1);
//		;
//
//	}	
	@Test
	public void testParseFischer() {
		String nameFile="/oliviaproject/pgn/test-fischer.pgn";
		InputStream is = FileUtils.getFileFromResourceAsStream(nameFile);

		assertNotNull(is);
		PGNReader reader = new PGNReader();
		List <GameStateMutable> games=reader.parseFile(is);
		assertNotNull(games);
		assertEquals(games.size(), 34);
		for(GameStateMutable game: games) {
			assertNotEquals(0, game.getMoves().size());
		}
		;
		for(GameStateMutable game: games) {
			for (int i=0;i<game.getMoves().size();i++) {
				Move m=game.getMoves().get(i);
			log.info(m.getPiece().name());
			log.info(m.getFrom());
			log.info(m.getTo());
			log.info("done!");
			}
		}

	}	
	@Test
	public void testde4() {
		IConvertor c =PGNReader.findConvertor("de4", new Convertors());
		assertNotNull(c);
	}	
	@Test
	public void teste4() {
		IConvertor c =PGNReader.findConvertor("e4", new Convertors());
		assertNotNull(c);
	}	
	@Test
	public void testa1Q() {
		IConvertor c =PGNReader.findConvertor("a1=Q", new Convertors());
		assertNotNull(c);
	}	

}
