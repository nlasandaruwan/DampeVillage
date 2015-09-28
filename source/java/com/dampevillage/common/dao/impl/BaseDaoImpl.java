package com.dampevillage.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dampevillage.common.dao.BaseDao;
import com.dampevillage.common.entity.BaseEntity;

public abstract class BaseDaoImpl<T extends BaseEntity, PK extends Serializable>
		extends HibernateDaoSupport implements BaseDao<T, PK> {

	/** common entity class reference. */
	protected Class<T> entityClass;

	/**
	 * default constructor.
	 * 
	 * @param entityClassValue
	 *            - common entity class.
	 * 
	 */
	public BaseDaoImpl(final Class<T> entityClassValue) {
		this.entityClass = entityClassValue;
	}

	/**
	 * Method to insert an domain object which is extended by an Persistent
	 * class.
	 * 
	 * @param entity
	 *            contains Persistent or Persistent sub class.
	 * @param id
	 *            -
	 * @throws Exception
	 * @throws CitiOlrException
	 *             - CitiCommonsException to be thrown.
	 * 
	 */
	public void createOrUpdate(T entity, PK id) throws Exception {
		int version = 0;
		version = ((BaseEntity) entity).getVersion();

		if (version == -1) {

			getHibernateTemplate().persist(entity);
		} else {
			T oldObject = getEntityById(id);
			if (oldObject == null) {
				throw new Exception("");
			}

			merge(entity);
		}
	}

	/**
	 * Method to flush persistance context.
	 * 
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * To get the list of all available domain specific objects.
	 * 
	 * @param start
	 *            - start value.
	 * @param max
	 *            - maximum no of reports.
	 * @return List of domain specific objects.
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(final int start, final Integer max) {

		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						return null;
					}
				});
	}

	/**
	 * To get a particular domain specific object by id.
	 * 
	 * @param id
	 *            - id of loading object.
	 * @return a domain specific objects.
	 */
	public T getEntityById(PK id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * To update a particular domain specific object already persisted.
	 * 
	 * @param entity
	 *            - object need to persist.
	 * @return a updated domain specific objects.
	 */
	public T merge(T entity) {
		return (T) getHibernateTemplate().merge(entity);
	}

	/**
	 * To remove a particular domain specific object already persisted.
	 * 
	 * @param entity
	 *            - object need to persist.
	 * @param id
	 *            -
	 * @throws Exception
	 * @throws CitiOlrException
	 *             - CitiCommonsException to be thrown.
	 */
	public void remove(T entity, PK id) throws Exception {
		int oldVersion = 0;
		int newVersion = 0;
		T oldObject = getEntityById(id);
		if (oldObject == null) {
			throw new Exception("");
		}

		oldVersion = ((BaseEntity) oldObject).getVersion();
		newVersion = ((BaseEntity) entity).getVersion();

		if (newVersion != oldVersion) {
			throw new Exception("");
		} else {
			getHibernateTemplate().delete(oldObject);
		}
	}
}
