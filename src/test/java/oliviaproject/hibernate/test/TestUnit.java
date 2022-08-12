package oliviaproject.hibernate.test;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import junit.framework.TestCase;
import oliviaproject.hibernate.ChessBoardPreference;
import oliviaproject.hibernate.UserName;

public class TestUnit extends TestCase {
	Map<Integer, UserName> users;
	private static final Logger log = LoggerFactory.getLogger(TestUnit.class);

	private static SessionFactory factory;

	
	public void setUp() {
		try {
			// Configuration configuration = new Configuration();
			// configuration.configure();
			// ServiceRegistry serviceRegistry = new
			// StandardServiceRegistryBuilder().applySettings(
			// configuration.getProperties()).build();

			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			;
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void testOneToOne() {
		Session session = factory.openSession();
		Transaction tx = null;

		ChessBoardPreference preference = new ChessBoardPreference();
		preference.setChesswidth(80);

// Add new Employee object
		UserName userName = new UserName();
		userName.setEmail("demo-user@mail.com");

// Save Account
		session.persist(preference);

		Assert.notNull(preference.getId());

// Save Employee
		userName.setPreference(preference);
		session.persist(userName);
		Assert.notNull(userName.getId());

		Assert.notNull(preference.getId());
	}
}