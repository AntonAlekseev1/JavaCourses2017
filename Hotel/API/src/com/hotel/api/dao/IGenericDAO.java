package com.hotel.api.dao;

import java.sql.Connection;
import java.util.List;

import com.hotel.api.been.IEntity;

public interface IGenericDAO<T extends IEntity> {
	
	public List<T> getAll(Connection connect) throws Exception;
	
	public T getById(Connection connect,Integer id);
	
	public void create(Connection connect,T entity) throws Exception;
	
	public void updute(Connection connect,T entity);
	
	public void delete(Connection connect,Integer id);

}
