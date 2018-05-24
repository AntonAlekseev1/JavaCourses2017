package com.blackbox.api.service;

import java.util.List;

import com.blackbox.beans.Event;
import com.blackbox.beans.Group;

/**
 * Interface for services classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IGroupService {
	/**
	 * This method returns a list of all groups that exist in the database
	 * 
	 * @return list of Events
	 * @throws Exception
	 */
	public List<Group> getAllGroups() throws Exception;

	/**
	 * This method return the group whose id is equal to the id passed to the method
	 * as a parameter
	 * 
	 * @param id
	 * @return {@link Group}
	 * @throws Exception
	 */
	public Group getGroupById(Integer id) throws Exception;

	/**
	 * This method return a group whose name is equal to the name passed to the
	 * method like as parameter
	 * 
	 * @param name
	 * @return {@link Group}
	 * @throws Exception
	 */
	public Group getGroupByName(String name) throws Exception;

	/**
	 * This method calls the database-level method to update an existing Group in
	 * the database, the changed Group is passed as the method parameter
	 * 
	 * @param group
	 * @throws Exception
	 */
	public void updateGroup(Group group) throws Exception;

	/**
	 * This method calls the database-level method to create a new Group that is
	 * passed to this method as a parameter
	 * 
	 * @param group
	 * @throws Exception
	 */
	public void createGroup(Group group) throws Exception;

	/**
	 * This method calls a database-level method to remove the group from the
	 * database, where the deletion group is selected by the id parameter
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void removeGroup(Integer id) throws Exception;

	public void addUserToGroup(Integer userId, Integer groupId) throws Exception;

	public void removeUserFromGroup(Integer userId, Integer groupId) throws Exception;

	public void addGroupEvent(Event event, Integer groupId) throws Exception;

	public void removeGroupEvent(Integer eventId, Integer groupId) throws Exception;

	public List<Group> getUserGroups(Integer id) throws Exception;

}
