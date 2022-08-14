package oliviaproject.io;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author HaoNguyen
 *
 */
public class OnChessBoardLauncher {

	public static final Logger logger = LoggerFactory.getLogger(OnChessBoardLauncher.class);

	public static void main(String[] args) throws IOException {
		/**
		 * OnChessBoardLauncher.class.getName would take the package, but that would not
		 * work out of eclipse because ClassPathXmlApplicationContext would take the .
		 * as / to find the file
		 */
		AppContext.setAppName("onchessboard");
		//AppContext.setAppName("onspring");
		ApplicationContext context = AppContext.getApplicationContext();
		AppContext.getProperties();
	}

}
