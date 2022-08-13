//package oliviaproject.hibernate.manager;
//
//import java.util.Collection;
//import java.util.HashMap;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.stereotype.Component;
//
//import oliviaproject.hibernate.sql.iSQL;
//import oliviaproject.spring.DefaultContext;
//
//@Component
//public class DefaultManager extends HashMap<Class, iSQL> {
//	@PostConstruct
//	public void init() {
//		Collection<iSQL> interfaces = DefaultContext.getAppContext().getBeansOfType(iSQL.class).values();
//		interfaces.forEach(filter -> this.put(filter.getName(), filter));
//
//	}
//
//}
