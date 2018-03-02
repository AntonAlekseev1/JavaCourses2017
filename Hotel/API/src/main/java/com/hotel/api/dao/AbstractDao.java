package com.hotel.api.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hotel.been.Entity;

public abstract class AbstractDao<T extends Entity> implements IGenericDao<T> {

	private static final Logger logger = Logger.getLogger(AbstractDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Session session, String name, Class<T> clazz) throws Exception {
		List<T> entityList = new ArrayList<>();
		try {
			Criteria criteria = session.createCriteria(clazz).addOrder(Order.asc(name));
			entityList = criteria.list();
		} catch (Exception e) {
			logger.error("Exception in the method getAll: ", e);
			throw new Exception("Exception in the method getAll " + e);
		}
		return entityList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Session session, Integer id, Class<T> clazz) throws Exception {
		T entity = null;
		try {
			Criteria criteria = session.createCriteria(clazz).add(Restrictions.like("id", id));
			entity = (T) criteria.uniqueResult();

		} catch (Exception e) {
			logger.error("Exception in the method getById: ", e);
			throw new Exception("Exception in the method getById " + e);
		}
		return entity;
	}

	@Override
	public void create(Session session, T entity) throws Exception {
		try {
			session.save(entity);

		} catch (Exception e) {
			logger.error("Exception in the method create: ", e);
			throw new Exception("Exception in the method create " + e);
		}

	}

	@Override
	public void updute(Session session, T entity) throws Exception {

		session.update(entity);
	}

	@Override
	public void saveOrUpdate(Session session, T entity) throws Exception {

		session.saveOrUpdate(entity);

	}

	@Override
	public void delete(Session session, T entity) throws Exception {
		try {
			session.delete(entity);

		} catch (Exception e) {
			logger.error("Exception in the method create: ", e);
			throw new Exception("Exception in the method create " + e);
		}
	}

}
