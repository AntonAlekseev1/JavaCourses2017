package com.hotel.api.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.hotel.api.been.Entity;
import com.hotel.api.been.IEntity;

public abstract class AbstractDao<T extends Entity> implements IGenericDao<T> {
	
	private static final Logger logger = Logger.getLogger(AbstractDao.class);

	@Override
	public List<T> getAll(Session session, String name) throws Exception {
		List<T> entityList = new ArrayList<>();
		try{
			Criteria criteria = session.createCriteria(Entity.class);
			entityList = (List<T>) criteria.addOrder(Order.asc(name));
		}catch (Exception e) {
			logger.error("Exception in the method getAll: ", e);
			throw new Exception("Exception in the method getAll "+e);
		}
		return entityList;
	}

	@Override
	public T getById(Session session, Integer id) throws Exception {
		T entity = null;
		try {
			Criteria criteria = session.createCriteria(Entity.class);
			entity = (T) criteria.list().get(id);
		}catch (Exception e) {
			logger.error("Exception in the method getById: ", e);
			throw new Exception("Exception in the method getById "+e);
		}
		return entity;
	}

	@Override
	public void create(Session session, T entity) throws Exception {
		try {
			session.save(entity);
			
		}catch (Exception e) {
			logger.error("Exception in the method create: ", e);
			throw new Exception("Exception in the method create "+e);
		}
		
	}

	@Override
	public void updute(Session session, T entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Session session, Integer id) throws Exception {
		
		
	}

}
