package com.hotel.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hotel.api.dao.IGenericDao;
import com.hotel.been.Entity;

public abstract class AbstractDao<T extends Entity> implements IGenericDao<T> {

	private static final Logger logger = Logger.getLogger(AbstractDao.class);
	private final String GET_ALL_EXCEPTION = "Exception in the method getAll: ";
	private final String GET_BY_ID_EXEPTION = "Exception in the method getById: ";
	private final String CREATE_EXCEPTION = "Exception in the method create: ";
	private final String UPDATE_EXCEPTION = "Exception in the method update: ";
	private final String DELETE_EXCEPTION = "Exception in the method delete: ";
	private final String SAVE_EXCEPTION = "Exception in the method save: ";
	private Class<T> clazz;
	
	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Session session, String name) throws Exception {
		List<T> entityList = new ArrayList<>();
		try {
			Criteria criteria = session.createCriteria(clazz).addOrder(Order.asc(name));
			entityList = criteria.list();
		} catch (Exception e) {
			logger.error(GET_ALL_EXCEPTION, e);
			throw new Exception(GET_ALL_EXCEPTION + e);
		}
		return entityList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Session session, Integer id) throws Exception {
		T entity = null;
		try {
			Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq("id", id));
			entity = (T) criteria.uniqueResult();

		} catch (Exception e) {
			logger.error(GET_BY_ID_EXEPTION, e);
			throw new Exception(GET_BY_ID_EXEPTION + e);
		}
		return entity;
	}

	@Override
	public void create(Session session, T entity) throws Exception {
		try {
			session.save(entity);

		} catch (Exception e) {
			logger.error(CREATE_EXCEPTION, e);
			throw new Exception(CREATE_EXCEPTION + e);
		}

	}

	@Override
	public void updute(Session session, T entity) throws Exception {
		try {
		session.update(entity);
		}catch(Exception e) {
			logger.error(UPDATE_EXCEPTION, e);
			throw new Exception(UPDATE_EXCEPTION+e);
		}
	}

	@Override
	public void saveOrUpdate(Session session, T entity) throws Exception {
		try {
		session.saveOrUpdate(entity);
		}catch(Exception e) {
			logger.error(SAVE_EXCEPTION, e);
			throw new Exception(SAVE_EXCEPTION + e);
		}

	}

	@Override
	public void delete(Session session, T entity) throws Exception {
		try {
			session.delete(entity);

		} catch (Exception e) {
			logger.error(DELETE_EXCEPTION, e);
			throw new Exception(DELETE_EXCEPTION + e);
		}
	}

}
