package com.blackbox.api.dao;

import java.util.List;

import com.blackbox.beans.generic.Entity;

/**
 * Generic interface for database layer classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IGenericDao<T extends Entity> {
	/**
	 * Implementation of this method makes a query to the database and returns all
	 * objects of type T without sorting if the parameter "orderBy" = null,
	 * otherwise returns a list sorted by the "orderBy" parameter passed to the
	 * method as a parameter
	 * 
	 * @return list of Entities
	 */
	public List<T> getAll(String name) throws Exception;

	/**
	 * Implementation of this method returns one unique object of type T with id
	 * equal to id passed to the method as a parameter
	 * 
	 * @return object of type T extends {@link Entity}
	 */
	public T getById(Integer id) throws Exception;

	/**
	 * Implementation of this method makes a new record in the database
	 */
	public void create(T entity) throws Exception;

	/**
	 * Implementation of this method updates an existing entry in the database
	 */
	public void update(T entity) throws Exception;

	/**
	 * Implementation of this method deletes the record from database
	 */
	public void delete(T entity) throws Exception;

}
