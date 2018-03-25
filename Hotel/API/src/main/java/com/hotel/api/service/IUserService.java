package com.hotel.api.service;

import com.hotel.been.User;

public interface IUserService {
	
	public void addUser(User user) throws Exception;
	
	public User getUser(String login) throws Exception;
	

}
