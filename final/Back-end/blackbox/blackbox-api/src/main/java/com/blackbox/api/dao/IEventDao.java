package com.blackbox.api.dao;

import java.util.List;

import com.blackbox.beans.Event;

/**
 * Interface for database layer classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IEventDao extends IGenericDao<Event> {
	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of group events with id equal to groupId
	 * 
	 * @return list of Events
	 */
	public List<Event> getGroupEvents(Integer groupId) throws Exception;

	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of user events with id equal to userId
	 * 
	 * @return list of Events
	 */
	public List<Event> getUserEvents(Integer userId) throws Exception;

}
