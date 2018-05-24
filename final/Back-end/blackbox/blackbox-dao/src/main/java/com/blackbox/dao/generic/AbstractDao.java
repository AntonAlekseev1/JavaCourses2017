package com.blackbox.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.blackbox.api.dao.IGenericDao;
import com.blackbox.beans.generic.Entity;

/**
 * 
 * Abstract parent class for all classes of work with the project data base.
 * Contains CRUD operations for working with objects inherited from Entity
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 * @param <T>
 */
public class AbstractDao<T extends Entity> implements IGenericDao<T> {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	private Class<T> clazz;

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * This method makes a query to the database and returns all objects of type T
	 * without sorting if the parameter "orderBy" = null, otherwise returns a list
	 * sorted by the "orderBy" parameter passed to the method as a parameter
	 * 
	 * @return list of Entities
	 */
	@Override
	public List<T> getAll(String orderBy) throws Exception {

		List<T> entityList = null;
		if (orderBy != null) {
			entityList = new ArrayList<>();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
			Root<T> root = criteriaQuery.from(clazz);
			criteriaQuery.select(root).orderBy(builder.asc(root.get(orderBy)));
			TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
			entityList = query.getResultList();
		} else {
			entityList = getAll();
		}
		return entityList;
	}

	/**
	 * Overloaded method getAll (), returns an unsorted list of objects of type T
	 * 
	 * @return list of Entities
	 * @throws Exception
	 */
	public List<T> getAll() throws Exception {

		List<T> entityList = new ArrayList<>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);
		TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
		entityList = query.getResultList();

		return entityList;
	}

	/**
	 * This method returns one unique object of type T with id equal to id passed to
	 * the method as a parameter
	 * 
	 * @return object of type T extends {@link Entity}
	 */
	@Override
	public T getById(Integer id) throws Exception {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		CriteriaQuery<T> byId = criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
		TypedQuery<T> query = entityManager.createQuery(byId);
		T entity = query.getSingleResult();

		return entity;
	}

	/**
	 * This method makes a new record in the database
	 */
	@Override
	public void create(T entity) throws Exception {

		entityManager.persist(entity);
	}

	/**
	 * This method updates an existing entry in the database
	 */
	@Override
	public void update(T entity) throws Exception {

		entityManager.merge(entity);
	}

	/**
	 * This method deletes the record from database
	 */
	@Override
	public void delete(T entity) throws Exception {

		entityManager.remove(entity);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
