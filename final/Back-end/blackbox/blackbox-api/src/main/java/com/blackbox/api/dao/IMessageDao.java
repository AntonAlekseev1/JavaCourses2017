package com.blackbox.api.dao;

import java.util.List;

import com.blackbox.beans.Message;

/**
 * Interface for database layer classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IMessageDao extends IGenericDao<Message> {
	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of dialog messages
	 * 
	 * @return List of Messages
	 */
	public List<Message> getDialogMessages(Integer dialogId) throws Exception;

}
