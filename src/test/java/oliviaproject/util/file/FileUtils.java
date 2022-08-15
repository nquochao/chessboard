package oliviaproject.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.chessboard.pgn.PGNReaderTest;
import oliviaproject.hibernate.entities.UserNameTest;

public class FileUtils {
	static final Logger log = LoggerFactory.getLogger(FileUtils.class);
	public static InputStream getFileFromResourceAsStream(String fileName) {

		
		InputStream inputStream = FileUtils.class.getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}

	}

	public static void printInputStream(InputStream is) {

		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {

			String line;
			while ((line = reader.readLine()) != null) {
				log.info(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
