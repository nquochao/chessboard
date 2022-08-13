package oliviaproject.hibernate.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.persistence.Query;
import oliviaproject.hibernate.entities.UserName;

@Component
public class UserNameSQL extends DefaultSQL<UserName> implements iSQL {
	private static final Logger log = LoggerFactory.getLogger(UserNameSQL.class);

	@Override
	public Class getName() {
		// TODO Auto-generated method stub
		return getEntity(new UserName());
	}

	@Override
	public UserName get(UserName u) {
		UserName result = null;
		/**
		 * the super check with the id so it is likely not to work when we check
		 * credentials
		 * 
		 */
		result=super.get(u);
		if (result== null) {
			result = checkCredentials(u);
		}
		return result;
	}

	public  UserName checkCredentials(UserName u) {
		Session session = factory.openSession();
		Query query = session.createNamedQuery("checkCredentials", UserName.class);
		query.setParameter("userName", u.getUserName());
		query.setParameter("password", u.getPassword());
		UserName user = (UserName) query.getSingleResult();
		if (user != null) {
			log.error("username and password are valid");
		} else {
			log.error("username and password are not valid");
		}
		session.close();
		return user;
	}

}