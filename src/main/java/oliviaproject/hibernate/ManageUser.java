package oliviaproject.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.User;

public class ManageUser {
	public void init() {
		try {
//	    	  Configuration configuration = new Configuration();
//	    	  configuration.configure();
//	    	  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//	    	  configuration.getProperties()).build();

				factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
				;
			} catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}		
	}
	private static SessionFactory factory;

	public static void main(String[] args) {
		ManageUser MU = new ManageUser();
		MU.init();

		/* Add few employee records in database */
		Integer empID1 = MU.addUser("calypso_user", "calypso", "hao.nguyen.perso@gmail.com");
		Integer empID2 = MU.addUser("calypso_test", "calypso", "hao.nguyen.perso@gmail.com");
		Integer empID3 = MU.addUser("olivia", "calypso", "hao.nguyen.perso@gmail.com");

		/* List down all the employees */
		MU.listEmployees();

		/* Update employee's records */
		MU.updateEmployee(empID1, "olivia.nguyen@gmail.com");

		/* Delete an employee from the database */
		MU.deleteEmployee(empID2);

		/* List down new list of the employees */
		MU.listEmployees();
	}

	/* Method to CREATE an employee in the database */
	public Integer addUser(String fname, String password, String email) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			UserName employee = new UserName();
			employee.setUserName(fname);
			employee.setPassword(password);
			employee.setEmail(email);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	public UserName getUser(String fname, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		UserName user = null;

		try {
			tx = session.beginTransaction();
			UserName employee = new UserName();
			employee.setUserName(fname);
			employee.setPassword(password);
			Query query = session.createQuery("from UserName where userName=:username and password=:password");
			query.setParameter("username", fname);
			query.setParameter("password", password);
			user = (UserName) query.uniqueResult();
			if (user != null) {
				System.out.println("username and password are valid");
			} else {
				System.out.println("username and password are not valid");
			}
			session.close();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List usernames = session.createQuery("FROM UserName").list();
			for (Iterator iterator = usernames.iterator(); iterator.hasNext();) {
				UserName username = (UserName) iterator.next();
				System.out.print("User: " + username.getUserName());
				System.out.print("  id: " + username.getId());
				System.out.println("  password: " + username.getPassword());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID, String email) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			UserName employee = (UserName) session.get(UserName.class, EmployeeID);

			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			UserName employee = (UserName) session.get(UserName.class, EmployeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}