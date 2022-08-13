package oliviaproject.io;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;


public class OnChessBoardLauncher {

	public static final Logger logger = LoggerFactory.getLogger(OnChessBoardLauncher.class);

	public static void main(String[] args) throws IOException {
		AppContext.setAppName(OnChessBoardLauncher.class.getName());
		ApplicationContext context = AppContext.getApplicationContext();
		AppContext.getProperties();
	}




}
