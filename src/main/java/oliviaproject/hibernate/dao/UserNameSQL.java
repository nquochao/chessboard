package oliviaproject.hibernate.dao;

import org.springframework.stereotype.Component;

import oliviaproject.hibernate.entities.UserName;

@Component
public class UserNameSQL extends DefaultSQL<UserName> implements iSQL{

	@Override
	public Class getName() {
		// TODO Auto-generated method stub
		return getEntity(new UserName());
	}
	@Override
	public UserName get(UserName u) {
	UserName result=null;
		if(super.get(u)==null) {
		
	}
	return result;
	}

}