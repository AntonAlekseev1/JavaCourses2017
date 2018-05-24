package com.blackbox.api.dao;

import java.util.List;

import com.blackbox.beans.User;

/**
 * Interface for database layer classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IUserDao extends IGenericDao<User> {
	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of the friends of the user whose "id" field is equal "userId" passed to
	 * the method like as parameter
	 * 
	 * @return List of Users
	 */
	public List<User> getFriends(Integer userId) throws Exception;

	/**
	 * Implementation of this method returns a list of users in the dialog, where
	 * the "id" of this dialog equals "dialogId", passed to the method like as
	 * parameter
	 * 
	 * @return List of Users
	 */
	public List<User> getDialogUsers(Integer dialogId) throws Exception;

	public User getEventUser(Integer eventId) throws Exception;

	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of users in the group, where the "id" of this group equals "groupId",
	 * passed to the method like as parameter
	 * 
	 * @return List of Users
	 */
	public List<User> getGroupUsers(Integer groupId) throws Exception;

	/**
	 * Implementation of this method returns the user that is the group
	 * administrator
	 * 
	 * @return {@link User}
	 */
	public User getGroupAdmin(Integer groupId) throws Exception;

	public User getByName(String name) throws Exception;

	/**
	 * Implementation of this method makes the query in the database and returns an
	 * object of the class {@link User} whose login is equal to the input login
	 * 
	 * @return {@link User}
	 */
	public User getByLogin(String login) throws Exception;

}
