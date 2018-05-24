package com.blackbox.api.service;

import java.util.List;

import com.blackbox.beans.Dialog;

/**
 * Interface for services classes
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
public interface IDialogService {

	/**
	 * This method return the dialog whose id is equal to the id passed to the
	 * method as a parameter
	 * 
	 * @param id
	 * @return {@link Dialog}
	 * @throws Exception
	 */
	public Dialog getById(Integer id) throws Exception;

	/**
	 * This method return the dialog whose header is equal to the name passed to the
	 * method as a parameter
	 * 
	 * @param name
	 * @return {@link Dialog}
	 * @throws Exception
	 */
	public Dialog getByHeader(String name) throws Exception;

	/**
	 * 
	 * This method calls the database-level method to create a new dialog that is
	 * passed to this method as a parameter
	 * 
	 * @param dialog
	 * @throws Exception
	 */
	public void createDialog(Dialog dialog) throws Exception;

	/**
	 * This method calls the database-level method to update an existing dialog in
	 * the database, the changed dialog is passed as the method parameter
	 * 
	 * @param dialog
	 * @throws Exception
	 */
	public void update(Dialog dialog) throws Exception;

	/**
	 * This method calls a database-level method to remove the dialog from the
	 * database, where the deletion dialog is selected by the id parameter
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void removeDialog(Integer id) throws Exception;

	/**
	 * This method returns a list of dialogs who are be in this dialog, where the
	 * dialog is selected from the database by the id parameter passed to the method
	 * as a parameter
	 * 
	 * @param id
	 * @return {@link Dialog}
	 * @throws Exception
	 */
	public List<Dialog> getUserDialogs(Integer id) throws Exception;

	/**
	 * 
	 * This method adds a user to the dialog where the dialog is selected from the
	 * database by the id parameter passed to the method as userId, and the dialog
	 * is also selected from the database by the id parameter passed to the method
	 * as dialogId
	 * 
	 * @param dialogId
	 * @param userId
	 * @throws Exception
	 */
	public void addUserToDialog(Integer dialogId, Integer userId) throws Exception;

	/**
	 * This method removes the user from the dialog where the dialog is selected
	 * from the database by the parameter id passed to the method as userId, and the
	 * dialog is also selected from the database by the id parameter passed to the
	 * method as dialogId
	 * 
	 * @param dialogId
	 * @param userId
	 * @throws Exception
	 */
	public void removeUserFromDialog(Integer dialogId, Integer userId) throws Exception;

	/**
	 * This method returns a dialog between two users where the first user is
	 * selected from the database by the id parameter passed to the method as
	 * firstId, and the second user is selected from the database by the id
	 * parameter passed to the method as secondId
	 * 
	 * @param firstId
	 * @param secondId
	 * @return {@link Dialog}
	 * @throws Exception
	 */
	public Dialog getDialogBetweenUsers(Integer firstId, Integer secondId) throws Exception;

}
