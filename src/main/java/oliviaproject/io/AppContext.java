package oliviaproject.io;

import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import oliviaproject.infra.spring.MyPropertyPlaceholderConfigurer;


public class AppContext {
	static ApplicationContext context;
	public static final Logger logger = LoggerFactory.getLogger(AppContext.class);

	static String appName;

	public static String getAppName() {
		return appName;
	}

	public static void setAppName(String appName) {
		AppContext.appName = appName;
	}

	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(new String[] { appName + "-applicationContext.xml" });
		}
		return context;
	}

	public static Properties getProperties() {
		ApplicationContext context = getApplicationContext();
		MyPropertyPlaceholderConfigurer configurer = context.getBean(MyPropertyPlaceholderConfigurer.class);
		Properties props = configurer.getProperties();
		Enumeration<String> enums = (Enumeration<String>) props.propertyNames();
		
		while (enums.hasMoreElements()) {
			String key = enums.nextElement();
			String value = props.getProperty(key);
			logger.info(key +":"+ value);
			
		}
		return props;
	}

	public static String getProperty(String keytofind) {

		ApplicationContext context = getApplicationContext();
		MyPropertyPlaceholderConfigurer configurer = context.getBean(MyPropertyPlaceholderConfigurer.class);
		Properties props = configurer.getProperties();
		Enumeration<String> enums = (Enumeration<String>) props.propertyNames();

		while (enums.hasMoreElements()) {
			String key = enums.nextElement();
			String value = props.getProperty(key);
			if (key.equals(keytofind)) {

				return value;
			}
			;

		}
		return null;
	}

	public static Boolean getBoolean(String keytofind) {
		String value = getProperty(keytofind);
		try {
			return Boolean.valueOf(value);
		} catch (Exception e) {
			return false;
		}
	}
}
