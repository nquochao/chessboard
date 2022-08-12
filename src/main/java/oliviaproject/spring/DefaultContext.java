package oliviaproject.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class DefaultContext {
	@Autowired
	protected static ApplicationContext appContext;

	public static ApplicationContext getAppContext() {
		return appContext;
	}
	
}
