package com.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import com.hotel.api.been.IEntity;

public interface IGenericDao<T extends IEntity> {
	
	public List<T> getAll(Session session, String name) throws Exception;

	public T getById(Session session, Integer id) throws Exception;

	public void create(Session session, T entity) throws Exception;

	public void updute(Session session, T entity) throws Exception;

	public void delete(Session session, Integer id) throws Exception;

}
