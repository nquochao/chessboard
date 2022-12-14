package oliviaproject.hibernate.entities;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import junit.framework.TestCase;

public class UserNameTest extends TestCase {
	Map<Integer, UserName> users;
	private static final Logger log = LoggerFactory.getLogger(UserNameTest.class);

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
		userName.setUserName("olivia");
		userName.setPassword("olivia");
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

	public static void testOneToOneAndCommitTx() {
		Session session = factory.openSession();
		/**
		 * 
		 * tx is needed to commit to database
		 */
		Transaction tx = session.beginTransaction();

		ChessBoardPreference preference = new ChessBoardPreference();
		preference.setChesswidth(80);

		UserName userName = new UserName();
		userName.setEmail("olivia@on.com");
		userName.setUserName("olivia");
		userName.setUserName("password");

		session.persist(preference);

		assertNotNull(preference.getId());

		userName.setPreference(preference);
		/**
		 * Query is not fired here.Object is made persistent. Data for user now
		 * available in first level cache but not in db.
		 */
		session.persist(userName);
		/**
		 * 
		 * So, it is always better to use Persist() rather than Save() as save has to be
		 * carefully used when dealing with Transient object
		 */
		assertNotNull(userName.getId());

		assertNotNull(preference.getId());
		/**
		 * Query will be fired at this point and data for user will now also be
		 * available in DB
		 * 
		 */
		session.getTransaction().commit();
		/**
		 * 
		 * if you play twice you need to change the userName Caused by:
		 * java.sql.SQLIntegrityConstraintViolationException: ORA-00001: unique
		 * constraint (CALYPSO_161.UK_APNNTI2FQ7NWY851I8QQATYT7) violated This is
		 * because {link UserName.userName gets the annotation: @Column(unique = true)
		 *
		 */

		session.close();
	}

}