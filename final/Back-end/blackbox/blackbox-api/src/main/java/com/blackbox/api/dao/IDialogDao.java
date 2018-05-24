package com.blackbox.api.dao;

import java.util.List;

import com.blackbox.beans.Dialog;

/**
 * Interface for database layer classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IDialogDao extends IGenericDao<Dialog> {
	/**
	 * Implementation of this method makes the query in the database and returns a
	 * list of user dialogs with id equal to userId passed to the method like as
	 * parameter
	 * 
	 * @return list of dialogs
	 */
	public List<Dialog> getUserDialogs(Integer userId) throws Exception;

	/**
	 * Implementation of this method makes the query in the database and returns a
	 * dialog whose header is equal to the header passed to the method like as
	 * parameter
	 * 
	 * @return {@link Dialog}
	 */
	public Dialog getByHeader(String name) throws Exception;

}
