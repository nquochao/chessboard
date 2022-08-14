package oliviaproject.hibernate.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oliviaproject.hibernate.entities.AbstractEntity;

public abstract class DefaultSQL<T extends AbstractEntity> implements iSQL {
	protected Map<Integer, T> beans;

	public Map<Integer, T> getBeans() {
		return beans;
	}

	public void setBeans(Map<Integer, T> beans) {
		this.beans = beans;
	}

	public static SessionFactory factory;

	public static SessionFactory getFactory() {
		return factory;
	}
@PostConstruct
	public void init() {
		try {

			if (factory == null)
				factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
		beans=new HashMap<>();
	}

	public Integer save(T bean) {

		Transaction tx = null;
		Integer id = null;
		Session session = null;
		try {
			session = DefaultSQL.getFactory().openSession();
			tx = session.beginTransaction();

			session.merge(bean);
			tx.commit();
			beans.put(bean.getId(), bean);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public void delete(T t, Integer id) {
		Session session = factory.openSession();
		Transaction tx = null;
		beans.remove(t.getId());

		try {
			tx = session.beginTransaction();
			T e = (T) session.get(t.getClass(), id);
			session.delete(e);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public T get(T t) {
		T result = null;
		if (beans.get(t.getId()) != null)
			result = beans.get(t.getId());
		else {
			Transaction tx = null;
			Integer id = t.getId();
			if (id == null)
				return result;
			Session session = null;
			try {
				session = DefaultSQL.getFactory().openSession();
				tx = session.beginTransaction();

				result = (T) session.get(t.getClass(), id);
				tx.commit();
				beans.put(t.getId(), t);
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return result;
	}
	Class getEntity(T t) {
		
			return t.getClass();
	}
}
