package com.blackbox.api.service;

import java.util.List;

import com.blackbox.beans.User;

public interface IUserService {
	
	public List<User> getAllUsers() throws Exception;

	public User getUserById(Integer id) throws Exception;

	public void addUser(User user) throws Exception;
	
	public void updateUser(User user) throws Exception;

	public void removeUser(Integer id) throws Exception;

	public List<User> search(String name) throws Exception;
	
	public User getUserByLogin(String name) throws Exception;
	
	public List<User> getFriends(Integer id) throws Exception;

	public void addToFriends(Integer userId, Integer friendId) throws Exception;

	public void removeFromFriends(Integer userId, Integer frendId) throws Exception;
	
	public List<User> getDialogUsers(Integer id) throws Exception;

	public List<User> getGroupUsers(Integer id) throws Exception;

	public User getGroupAdmin(Integer id) throws Exception;


}
