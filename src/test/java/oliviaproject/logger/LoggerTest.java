
	package oliviaproject.logger;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	 
	public class LoggerTest {
	    private static final Logger log = LoggerFactory.getLogger(LoggerTest.class);
	 
	    public static  void test() {
	        log.info("info message");
	        log.debug("debugging");
	    }
	 
	    public static void main ( String [] args ) {
	        test();
	    }
	}
	 