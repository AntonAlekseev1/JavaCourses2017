package com.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.been.Entity;

public interface IGenericDao<T extends Entity> {

	public List<T> getAll(Session session, String name, Class<T> claz) throws Exception;

	public T getById(Session session, Integer id, Class<T> clazz) throws Exception;

	public void create(Session session, T entity) throws Exception;

	public void updute(Session session, T entity) throws Exception;

	public void delete(Session session, T entity) throws Exception;

	public void saveOrUpdate(Session session, T entity) throws Exception;

}
