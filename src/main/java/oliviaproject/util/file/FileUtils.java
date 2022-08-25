package oliviaproject.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
	public static InputStream getFromPath(String filePath) {
		try {
			Path p= Paths.get(filePath);
			return Files.newInputStream(p, StandardOpenOption.READ);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static InputStream getFromURL(String filePath) {
//InputStream input = new URL("http://www.somewebsite.com/a.txt").openStream();
//URL fileURL = new URL("file://C:/RAdev/Basic/src/test/resources/xml Data/test dir/app-config-seed-data.xml");
//		is = fileURL.openStream();
		
InputStream inputStream = null;
try {
	inputStream = new URL(filePath).openStream();

if (inputStream == null) {
	throw new IllegalArgumentException("file not found! " + filePath);
} else {
	return inputStream;
}
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
return inputStream;


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
