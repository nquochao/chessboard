package oliviaproject.hibernate.sql;

import org.springframework.stereotype.Component;

import oliviaproject.hibernate.UserName;

@Component
public class UserNameSQL extends DefaultSQL<UserName> implements iSQL{

	@Override
	public Class getName() {
		// TODO Auto-generated method stub
		return getEntity(new UserName());
	}


}