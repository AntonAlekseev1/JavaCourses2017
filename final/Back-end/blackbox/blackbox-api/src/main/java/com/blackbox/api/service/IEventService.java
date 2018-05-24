package com.blackbox.api.service;

import java.util.List;

import com.blackbox.beans.Event;

/**
 * Interface for services classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IEventService {

	/**
	 * 
	 * This method calls the database-level method to create a new Event that is
	 * passed to this method as a parameter
	 * 
	 * @param dialog
	 * @throws Exception
	 */
	public void createEvent(Event event) throws Exception;

	/**
	 * This method calls a database-level method to remove the Event from the
	 * database, where the deletion Event is selected by the id parameter
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void removeEvent(Integer id) throws Exception;

	/**
	 * 
	 * This method returns a list of user events where the user is selected from the
	 * database by the id parameter to the id passed to the method as a parameter
	 * 
	 * @param id
	 * @return list of Events
	 * @throws Exception
	 */
	public List<Event> getUserEvents(Integer id) throws Exception;

	/**
	 * 
	 * This method returns a list of group events where the group is selected from the
	 * database by the id parameter to the id passed to the method as a parameter
	 * 
	 * @param id
	 * @return list of Events
	 * @throws Exception
	 */
	public List<Event> getGroupEvents(Integer id) throws Exception;

}
