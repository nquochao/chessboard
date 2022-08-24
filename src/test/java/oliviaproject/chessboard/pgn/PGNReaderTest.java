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
			log.info(m.toString());
			}
			log.info("done!");

		}

	}	
	@Test
	public void testdxe4() {
		String sanMove="dxe4";
		IConvertor c =PGNReader.findConvertor(sanMove, new Convertors(), true);
		assertNotNull(c);
		String r=PGNReader.findPositionTo(sanMove, new Convertors(), true);
		assertEquals("3-4", r);
		r= c.getBeforeValue();
		assertEquals("3", r);
		
	}
	@Test
	public void testd3xe4() {
		String sanMove="d3xe4";
		IConvertor c =PGNReader.findConvertor(sanMove, new Convertors(), true);
		assertNotNull(c);
		String r=PGNReader.findPositionTo(sanMove, new Convertors(), true);
		assertEquals("3-4", r);
		r= c.getBeforeValue();
		assertEquals("2-3", r);
		
	}
	@Test
	public void test3xe4() {
		String sanMove="3xe4";
		IConvertor c =PGNReader.findConvertor(sanMove, new Convertors(), true);
		assertNotNull(c);
		String r=PGNReader.findPositionTo(sanMove, new Convertors(), true);
		assertEquals("3-4", r);
		r= c.getBeforeValue();
		assertEquals("2", r);
		
	}
	@Test
	public void testde4() {
		String sanMove="de4";
		IConvertor c =PGNReader.findConvertor(sanMove, new Convertors(), true);
		assertNotNull(c);
		String r=PGNReader.findPositionTo(sanMove, new Convertors(), true);
		assertEquals("3-4", r);
		r= c.getBeforeValue();
		assertEquals("3", r);
		
	}	
	@Test
	public void teste4() {
		String sanMove="e4";
		IConvertor c =PGNReader.findConvertor(sanMove, new Convertors(), true);
		assertNotNull(c);
		String r=PGNReader.findPositionTo(sanMove, new Convertors(), true);
		assertEquals("3-4", r);
		r= c.getBeforeValue();
		assertEquals("3-4", r);

	}	
	/**
	 * to remove the comments
	 */
	@Test
	public void testa1Q() {
		String sanMove="a1=Q";
		IConvertor c =PGNReader.findConvertor(sanMove, new Convertors(), true);
		assertNotNull(c);
		PGNReader.findPositionTo(sanMove, new Convertors(), true);
		String r=PGNReader.findPositionTo(sanMove, new Convertors(),true);
		assertEquals("0-0", r);
		r= c.getBeforeValue();
		assertEquals("", r);
	}	

}
