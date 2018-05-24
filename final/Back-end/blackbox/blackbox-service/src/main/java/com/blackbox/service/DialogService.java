package com.blackbox.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackbox.api.dao.IDialogDao;
import com.blackbox.api.dao.IUserDao;
import com.blackbox.api.service.IDialogService;
import com.blackbox.beans.Dialog;
import com.blackbox.beans.User;

/**
 * The Class DialogService that manages dialogs.
 * 
 * @author Anton Alekseev
 * @version 0.0.1-SNAPSHOT
 */
@Service("dialogService")
@Transactional
public class DialogService implements IDialogService {

	@Autowired
	private IDialogDao dialogDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public Dialog getById(Integer id) throws Exception {

		return dialogDao.getById(id);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Dialog getByHeader(String name) throws Exception {

		return dialogDao.getByHeader(name);
	}

	@Override
	public void createDialog(Dialog dialog) throws Exception {

		dialogDao.create(dialog);
	}

	@Override
	public void update(Dialog dialog) throws Exception {
		dialogDao.update(dialog);
	}

	@Override
	public void removeDialog(Integer id) throws Exception {

		dialogDao.delete(dialogDao.getById(id));
	}

	@Override
	public void addUserToDialog(Integer dialogId, Integer userId) throws Exception {

		userDao.getDialogUsers(dialogId).add(userDao.getById(userId));
	}

	@Override
	public void removeUserFromDialog(Integer dialogId, Integer userId) throws Exception {

		userDao.getDialogUsers(dialogId).remove(userDao.getById(userId));
	}

	@Override
	public List<Dialog> getUserDialogs(Integer id) throws Exception {

		return dialogDao.getUserDialogs(id);
	}

	@Override
	public Dialog getDialogBetweenUsers(Integer firstId, Integer secondId) throws Exception {
		User firstUser = userDao.getById(firstId);
		User secondUser = userDao.getById(secondId);
		List<Dialog> allDialogs = dialogDao.getAll(null);
		Dialog dialogue = null;
		for (Dialog dialog : allDialogs) {
			if (dialog.getDialogUsers().contains(firstUser) && dialog.getDialogUsers().contains(secondUser)) {
				dialogue = dialog;
			}
		}
		if (dialogue == null) {
			Dialog dialog = new Dialog();
			dialogDao.create(dialog);
			Set<User> dialogUsers = new HashSet<>();
			dialogUsers.add(firstUser);
			dialogUsers.add(secondUser);
			dialog.setDialogUsers(dialogUsers);
			dialog.setHeader(firstUser.getName() + "/" + secondUser.getName());
			dialogDao.update(dialog);
			dialogue = dialog;
		}
		return dialogue;
	}

}
