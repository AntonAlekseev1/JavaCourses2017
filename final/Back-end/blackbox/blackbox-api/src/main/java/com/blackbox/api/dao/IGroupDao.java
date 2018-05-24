package com.blackbox.api.dao;

import java.util.List;

import com.blackbox.beans.Group;

/**
 * Interface for database layer classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IGroupDao extends IGenericDao<Group> {
	/**
	 * Implementation of this method makes the query in the database and returns the
	 * group of which the event belongs
	 * 
	 * @return {@link Group}
	 */
	public Group getEventGroup(Integer eventId) throws Exception;

	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of user groups with id equal to userId
	 * 
	 * @return list of groups
	 */
	public List<Group> getUserGroups(Integer userId) throws Exception;

	/**
	 * Implementation of this method makes the query in the database and returns a
	 * group whose name is equal to the name passed to the method like as parameter
	 * 
	 * @return {@link Group}
	 */
	public Group getByName(String name) throws Exception;

}
