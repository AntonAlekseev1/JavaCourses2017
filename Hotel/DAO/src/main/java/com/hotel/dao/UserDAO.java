package com.hotel.dao;

import com.hotel.api.dao.IUserDAO;
import com.hotel.been.User;

public class UserDAO extends AbstractDao<User> implements IUserDAO{
	
	private static UserDAO instance;

	private UserDAO() {
		super(User.class);
		
	}

	public static UserDAO getInstance() {
		if(instance==null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
}
