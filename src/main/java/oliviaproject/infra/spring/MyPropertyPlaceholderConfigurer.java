package oliviaproject.infra.spring;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * A extension that handles encryption for database password
 * 
 *
 */
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	Properties properties;
	

	
	

	public Properties getProperties(){
		// TODO Auto-generated method stub
		
		if(properties==null)
			try {
				properties= super.mergeProperties();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return properties;
	}
	
}