package oliviaproject.hibernate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class DefaultEntity implements IEntity{
	@Id
    @GeneratedValue

	Integer id;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}
}
