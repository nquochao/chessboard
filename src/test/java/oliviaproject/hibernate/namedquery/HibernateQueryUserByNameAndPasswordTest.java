package oliviaproject.hibernate.namedquery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Query;
import junit.framework.TestCase;
import oliviaproject.hibernate.entities.ChessBoardPreference;
import oliviaproject.hibernate.entities.UserName;

public class HibernateQueryUserByNameAndPasswordTest extends TestCase {
	private static final Logger log = LoggerFactory.getLogger(HibernateQueryUserByNameAndPasswordTest.class);

	private static SessionFactory factory;

	public void setUp() {
		try {

			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			;
		} catch (Throwable ex) {
			log.error("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
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
		userName.setEmail("demo-user@mail.com");
		userName.setUserName("olivia");
		userName.setPassword("olivia");

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

	public static void testQueryUser() {
		Session session = factory.openSession();
		Query query = session.createQuery("from UserName where userName=:userName and password=:password");
		query.setParameter("userName", "olivia");
		query.setParameter("password", "olivia");
		UserName user = (UserName) ((org.hibernate.query.Query) query).uniqueResult();
		if (user != null) {
			System.out.println("username and password are valid");
		} else {
			System.out.println("username and password are not valid");
		}
		session.close();
	}

	public static void testQueryUserByNamedQuery() {
		Session session = factory.openSession();
		Query query = session.createNamedQuery("checkCredentials", UserName.class);
		query.setParameter("userName", "olivia");
		query.setParameter("password", "olivia");
		UserName user = (UserName) query.getSingleResult();
		if (user != null) {
			System.out.println("username and password are valid");
		} else {
			System.out.println("username and password are not valid");
		}
		session.close();
	}
}
