package com.hotel.api.dao;

import org.hibernate.Session;

import com.hotel.been.User;

public interface IUserDAO extends IGenericDao<User>{
	
	public User getByLogin(Session session, String login) throws Exception;

}
