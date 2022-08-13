package oliviaproject.hibernate.entities;

public abstract class AbstractEntity implements IEntity{

	@Override
	public abstract Integer getId();

	@Override
	public abstract void setId(Integer id);

}
